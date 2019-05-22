/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aıproject1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class UninformedSearch {
    public UninformedSearch(){
        
    }
    public List<Node> BreadthFirstSearch(Node root){
        List<Node> PathToSolution=new ArrayList<Node>();
        List<Node> OpenList=new ArrayList<Node>();
        List<Node> ClosedList=new ArrayList<Node>();
        
        OpenList.add(root);
        boolean GoalFound=false;
        
        while(OpenList.size()>0 && !GoalFound){
            Node currentNode=OpenList.get(0);
            ClosedList.add(currentNode);
            OpenList.remove(0);
            
            currentNode.ExpandNode();
            currentNode.PrintPuzzle();
            for(int i=0;i<currentNode.children.size();i++){
                Node currentChild=currentNode.children.get(i);
                if(currentChild.GoalTest()){
                    System.out.println(" ");
                    System.out.println("Goal found");
                    GoalFound=true;
                    PathTrace(PathToSolution,currentChild);      
                    break;
                }
                
                if(!Contains(OpenList,currentChild)&&!Contains(ClosedList,currentChild)){
                    OpenList.add(currentChild);
                }
            }
        }
        return PathToSolution;
    }
     public List<Node> DepthFirstSearch(Node root){
        List<Node> PathToSolution=new ArrayList<Node>();
        List<Node> OpenList=new ArrayList<Node>();
        List<Node> ClosedList=new ArrayList<Node>();
        
        OpenList.add(root);
        boolean GoalFound=false;
        
        while(OpenList.size()>0 && !GoalFound){
            Node currentNode=OpenList.get(0);
            ClosedList.add(currentNode);
            OpenList.remove(0);
            
            currentNode.ExpandDFSNode();
            currentNode.PrintPuzzle();
            for(int i=0;i<currentNode.children.size();i++){
                Node currentChild=currentNode.children.get(i);
                if(currentChild.GoalTest()){
                    System.out.println(" ");
                    System.out.println("Goal found");
                    GoalFound=true;
                    PathTrace(PathToSolution,currentChild);
                }
                
                if(!Contains(OpenList,currentChild)&&!Contains(ClosedList,currentChild)){
                    OpenList.add(currentChild);
                }
            }
        }
        return PathToSolution;
     }
    
   
    public void PathTrace(List<Node> path,Node n){
        Node current=n;
        path.add(current);
        
        while(current.parent!=null){
            current=current.parent;
            path.add(current);
        }
    }

    private boolean Contains(List<Node> list, Node c) {
        boolean contains=false;
        for(int i=0;i<list.size();i++){
            if(list.get(i).IsSamePuzzle(c.puzzle)){
                contains=true;
            }
        }
        return contains;
    }
    
}
