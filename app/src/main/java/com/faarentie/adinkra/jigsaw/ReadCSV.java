package com.faarentie.adinkra.jigsaw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;

import com.faarentie.adinkra.jigsaw.R;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.content.res.Resources;

public class ReadCSV {
	Context mContext;
	
	public ReadCSV(Context context) {
		// TODO Auto-generated constructor stub\
		this.mContext = context;
	}
	
	public ArrayList<PuzzEntity> getPuzzList(){
		ArrayList<PuzzEntity> puzzList = new ArrayList<PuzzEntity>();
		InputStream is = mContext.getResources().openRawResource
				
	            (R.raw.data);
	                BufferedReader reader = new BufferedReader(new InputStreamReader
	            (is));
	                try {
	                    String line;
	                    while ((line = reader.readLine()) != null) {
	                        // do something with "line"
//	                    	Log.d("ThangTB", "line:"+line);
	                        String[] RowData = line.split(";");
	                        ArrayList<String> parts = new ArrayList<String>();
	                        ArrayList<Point> points = new ArrayList<Point>();
	                        
	                        for (int i = 4; i < 13; i++) {
								parts.add(RowData[i]);
							}
	                        
	                        Point p;
	                        int f =0;
	                        int x =0;
	                        int y=0;
	                        
	                        for (int i = 13; i< RowData.length; i++) {
	                        	if (f%2==0) {
	                        		x = Integer.parseInt(RowData[i]);
								}else if (f%2==1){
									y = Integer.parseInt(RowData[i]);
									p = new Point(x, y);
									points.add(p);
									
								}
	                        	f++;
							}
	                         PuzzEntity entity = new PuzzEntity(RowData[0], RowData[1], RowData[2], RowData[3], parts, points);
	                         
	                         puzzList.add(entity);
	                    }
	                }catch (IOException ex) {
	                    // handle exception
	                }finally {
	                    try {
	                        is.close();
	                    }
	                    catch (IOException e) {
	                        // handle exception
	                    }
	                }
		return puzzList;
	}

}
