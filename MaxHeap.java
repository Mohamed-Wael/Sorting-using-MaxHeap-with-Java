/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxheap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohamed Wael
 */
public class MaxHeap {
    void buildmaxheap(int[] arr)
    {
        int n = arr.length; 
        for (int i = n / 2 - 1; i >= 0; i--) 
            maxheapify(arr, n, i);
    }
    public void heapsort(int[] arr) 
    { 
        buildmaxheap(arr);
        int n = arr.length;
        for (int i=n-1; i>=0; i--) 
        {  
            int temp = arr[0]; 
            arr[0] = arr[i]; 
            arr[i] = temp; 
            maxheapify(arr, i, 0); 
        } 
    } 
  
    void maxheapify(int[] arr, int n, int i) 
    { 
        int largest = i;  
        int l = 2*i + 1;  
        int r = 2*i + 2;  
        if (l < n && arr[l] > arr[largest]) 
            largest = l;  
        if (r < n && arr[r] > arr[largest]) 
            largest = r; 
        if (largest != i) 
        { 
           int temp = arr[i]; 
            arr[i] = arr[largest]; 
            arr[largest] = temp; 
            maxheapify(arr, n, largest); 
        } 
    }
    
    int getFileSize(String filename)
    {
        Scanner x = null;
        int i = 0;
        int temp;
        try { 
            x = new Scanner(new File(filename));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MaxHeap.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(x.hasNext())
        {
            temp = x.nextInt();
            i++;
        }
        return i;
    }
    
    void fillArrayFromFile(int[] arr, String filename)
    {
        int i = 0;
        Scanner x = null;
        try { 
            x = new Scanner(new File(filename));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MaxHeap.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(x.hasNext())
        {
            arr[i] = x.nextInt();
            i++;
        }
    }
    
    void fillFile(String filename, int size) throws IOException
    {
        Random rand = new Random();
        int random;
        int i = 1;
        FileWriter fs = new FileWriter(filename, false);
        BufferedWriter fr = new BufferedWriter(fs);
        for(i=1;i<=size;i++)
        {
           random = rand.nextInt(100000);
           fr.write(String.valueOf(random));
           fr.write(" ");
        }
        fr.close();
        
         
    }
    
    void printArray(int[] arr)
    {
        int n = arr.length;
        for(int i =0;i<n;i++)
            System.out.print(arr[i]+" ");
        System.out.println("");
    }
  
    public static void main(String args[]) throws IOException 
    {   
        String filename = "numbers.txt";
        int size = 50;
        MaxHeap h = new MaxHeap();
        //h.fillFile(filename,size);
        int filesize = h.getFileSize(filename);
        int[] arr = new int[filesize];
        h.fillArrayFromFile(arr, filename);
        System.out.println("Initial order:");
        h.printArray(arr);
        double start = System.currentTimeMillis();
        h.heapsort(arr); 
        double end = System.currentTimeMillis();
        System.out.println("Sorted order:");
        h.printArray(arr);
        System.out.println("Time Taken for heapsort: " +(end-start)+"ms");
    } 

    
}
