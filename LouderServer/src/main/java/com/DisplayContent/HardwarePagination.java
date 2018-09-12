package com.DisplayContent;

import com.Databases.HardwareInfoCrud;

/**
 * Created by mihai on 3/14/2018.
 */
public class HardwarePagination {

    public static int curent_page=0,previous_step=0,next_step=0,all_pages=0;

    public HardwarePagination(){}


    public static void pagination_init(){
        HardwareInfoCrud gb_operation= new HardwareInfoCrud();
        all_pages=gb_operation.get_count_items();

        if(curent_page==0 ){
            if (all_pages >= 5) {
                next_step = 5;
                curent_page = 1;
                previous_step = 1;
            } else {
                next_step = all_pages;
                curent_page = 1;
                previous_step = 1;
            }
        }
        else if(all_pages<=5){
            next_step = all_pages;
            previous_step = 1;
            if(curent_page>all_pages)curent_page=previous_step;
        }
        else if(all_pages>=5){
            if((previous_step+4)!=next_step){
                next_step=(previous_step+4);
            }
        }

    }


    public static int get_curent_page(){
        return curent_page;
    }


    public static int get_next_page(){
        return next_step;
    }


    public static int get_previous_page(){return previous_step;}


    public static boolean set_curent_page(int page){
        if(curent_page<=next_step && curent_page>=previous_step){curent_page=page;}
        else{return false;}
        return true;
    }


    public static boolean set_next_page(){

        if(next_step+1<=all_pages){
            ++next_step;
            ++previous_step;
            if(curent_page<previous_step)curent_page=previous_step;
            return true;
        }
        return false;

    }


    public static boolean set_previous_page(){
        if(previous_step-1>0){
            --previous_step;
            --next_step;
            if(curent_page>next_step)curent_page=next_step;
            return true;
        }
        return false;
    }

}
