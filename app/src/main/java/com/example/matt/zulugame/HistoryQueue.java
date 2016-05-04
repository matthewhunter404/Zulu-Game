package com.example.matt.zulugame;

/**
 * Created by matt on 2016/05/04.
 */
public class HistoryQueue {
    int queueLength;
    int queue[];

    public HistoryQueue(int pQueueLength){
        queueLength=pQueueLength;
        queue= new int[queueLength];
        for(int k=0;k<pQueueLength;k++){
            queue[k]=-1;
        }
    }
    public void add(int pNewItem){
        for(int k=0;k<(queueLength-1);k++){
            queue[k+1]=queue[k];
        }
        queue[0]=pNewItem;
    }
    public boolean checkItemHistory(int pItem){
        boolean answer=true;
        for(int k=0;k<(queueLength);k++){
            if(pItem==queue[k]){
                answer=false;
            }
        }
      return answer;
    }
}
