/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aıproject1;

import java.io.File;
import java.util.List;
import java.util.Iterator;
import java.util.Vector;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import java.io.FileInputStream;
/**
 *
 * @author ASUS
 */
public class AIProject1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
 
	String fileName="C:\\Users\\ASUS\\Desktop\\dataa.xls"; 
	//Read an Excel File and Store in a Vector
        Vector dataHolder=readExcelFile(fileName);
        

        Vector cellStoreVector=(Vector)dataHolder.elementAt(0);
        HSSFCell myCell = (HSSFCell)cellStoreVector.elementAt(0);
        double temp=myCell.getNumericCellValue();
        int puzzlelength=(int)temp;
        int length=puzzlelength*puzzlelength;
        int[] puzzle=new int[length];
        int a=0;
        try {  
            for (int i=1;i<puzzlelength+1; i++){
                cellStoreVector=(Vector)dataHolder.elementAt(i);
                for (int j=0; j < puzzlelength;j++){
                    myCell = (HSSFCell)cellStoreVector.elementAt(j);
                    puzzle[a]=(int)myCell.getNumericCellValue();
                    a++;
                }
                
            }
        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        
        
        Node root  =new Node(puzzle,length);
        UninformedSearch ui=new UninformedSearch();
 
        System.out.println("BFS solution");

        List<Node> solution=ui.BreadthFirstSearch(root);
        
        if(solution.size()>0){
            
            for(int i=0;i<solution.size();i++){
                solution.get(i).PrintPuzzle();
            }
        }
        else{
            System.out.println("No path to solution is found.");
        }
    }
    public static Vector readExcelFile(String fileName){
        Vector cellVectorHolder = new Vector();
        try{
            FileInputStream myInput = new FileInputStream(fileName);
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

            HSSFSheet mySheet = myWorkBook.getSheetAt(0);

              Iterator rowIter = mySheet.rowIterator();
              while(rowIter.hasNext()){
                  HSSFRow myRow = (HSSFRow) rowIter.next();
                  Iterator cellIter = myRow.cellIterator();
                  Vector cellStoreVector=new Vector();
                  while(cellIter.hasNext()){
                      HSSFCell myCell = (HSSFCell) cellIter.next();
                      cellStoreVector.addElement(myCell);
                  }
                  cellVectorHolder.addElement(cellStoreVector);
              }
        }catch (Exception e){e.printStackTrace(); }
        return cellVectorHolder;
}
    
}
