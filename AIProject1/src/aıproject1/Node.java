/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aıproject1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Node {
    
    public List<Node> children=new ArrayList<Node>();
    public Node parent;
    public int[] puzzle;
    public int x=0;
    public int col=3;
    
    public Node(int[] p, int length){
        
        SetPuzzle(p,length);
        col=(int) Math.sqrt(length);
    }

   
    
    public void SetPuzzle(int[] p,int length){
        puzzle=new int[length];
        for(int i=0;i<length;i++){
            this.puzzle[i]=p[i];
        }
    }
    public void ExpandNode(){
        for(int i=0;i<puzzle.length;i++){
            if(puzzle[i]==0){
                x=i;
            }
        }
        MoveToRight(puzzle,x);
        MoveToLeft(puzzle,x);
        MoveToUp(puzzle,x);
        MoveToDown(puzzle,x);
    }
       public void ExpandDFSNode(){
        for(int i=0;i<puzzle.length;i++){
            if(puzzle[i]==0){
                x=i;
            }
        }
        
        MoveToLeft(puzzle,x);
        MoveToUp(puzzle,x);
        MoveToRight(puzzle,x);
        MoveToDown(puzzle,x);
    }
    public void MoveToRight(int[] p,int i){
        if(i%col<col-1){
            int[] pc=new int[p.length];
            CopyPuzzle(pc,p);
            
            int temp=pc[i+1];
            pc[i+1]=pc[i];
            pc[i]=temp;
            
            Node child=new Node(pc,p.length);
            children.add(child);
            child.parent=this;
        }
    }
    public void MoveToRightDFS(int[] p,int i){
        if(i%col<col-1){
            int[] pc=new int[p.length];
            CopyPuzzle(pc,p);
            
            int temp=pc[i+1];
            pc[i+1]=pc[i];
            pc[i]=temp;
            
            Node child=new Node(pc,p.length);
            children.add(child);
            child.parent=this;
        }
    }
    public void MoveToLeft(int[] p,int i){
        if(i%col>0){
            int[] pc=new int[p.length];
            CopyPuzzle(pc,p);
            
            int temp=pc[i-1];
            pc[i-1]=pc[i];
            pc[i]=temp;
            
            Node child=new Node(pc,p.length);
            children.add(child);
            child.parent=this;
        }
    }
    public void MoveToUp(int[] p,int i){
        if(i-col>=0){
            int[] pc=new int[p.length];
            CopyPuzzle(pc,p);
            
            int temp=pc[i-col];
            pc[i-col]=pc[i];
            pc[i]=temp;
            
            Node child=new Node(pc,p.length);
            children.add(child);
            child.parent=this;
        }
    }
    public void MoveToDown(int[] p,int i){
         if(i+col<puzzle.length){
            int[] pc=new int[p.length];
            CopyPuzzle(pc,p);
            
            int temp=pc[i+col];
            pc[i+col]=pc[i];
            pc[i]=temp;
            
            Node child=new Node(pc,p.length);
            children.add(child);
            child.parent=this;
        }
    }
    public void PrintPuzzle(){

        System.out.println("");
        int m=0;
        for(int i=0;i<col;i++){
            for(int j=0;j<col;j++){
                System.out.print(puzzle[m]+" ");
                m++;
            }
            System.out.println("");
        }
    }
    
    public boolean IsSamePuzzle(int[] p){
        boolean samePuzzle=true;
        for(int i=0;i<p.length;i++){
            if(puzzle[i]!=p[i]){
                samePuzzle=false;
            }
        }
        return samePuzzle;
    }
    public void CopyPuzzle(int[] a,int[] b){
        for(int i=0;i<b.length;i++){
            a[i]=b[i];
        }
    }
    
    public boolean GoalTest(){
        boolean isGoal=true;
        int m=puzzle[0];
        for(int i=0;i<puzzle.length;i++){
            if(m>puzzle[i]){
                isGoal=false;
            }
            m=puzzle[i];
        }
        return isGoal;
    }
}
