package com.example.nebalbarhoome.home3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class DataBase implements Serializable {
    public final static String fName = "database.serialized";
    private static DataBase ourInstance = new DataBase();
    public static void createSampleData(){
        DataBase dataBase=new DataBase();
        dataBase.addPage(new FavoritePage("Google","https://www.google.com/",0));
        dataBase.addPage(new FavoritePage("Yahoo","https://www.yahoo.com/",0));
        dataBase.addPage(new FavoritePage("Msn","https://www.msn.com/",0));
        dataBase.addPage(new FavoritePage("westminster","http://www.westminster.edu/index.cfm",0));
        dataBase.addPage(new FavoritePage("developer.android","https://developer.android.com/index.html",0));
        dataBase.addPage(new FavoritePage("cplusplus","http://www.cplusplus.com/",0));
        dataBase.addPage(new FavoritePage("Geeksforgeeks","https://www.geeksforgeeks.org/",0));
        dataBase.addPage(new FavoritePage("GitHub","https://github.com/",0));
        ourInstance =dataBase;
    }

    public static DataBase getInstance() {
        if (ourInstance == null)
            ourInstance = new DataBase();
        return ourInstance;
    }

    private DataBase() {
        pages = new ArrayList<>();
    }

    private List<FavoritePage> pages;


//load the database (into the singleton variable) from the specified file

    public static void load(File f) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        ourInstance = (DataBase) ois.readObject();
        ois.close();
    }


//save this database to the specified file

    public void save(File f) throws IOException{

        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(f));
        oout.writeObject(this);
        oout.close();

    }

//return the site at the specified index

    public int getPageCount() {

        return pages.size();

    }

//return the list of sites, wrapped to be “unmodifiable”

    public FavoritePage getPage(int index) {

        return pages.get(index);

    }

    public List<FavoritePage> getPages() {

        return pages;

    }

    public void addPage(FavoritePage p){
        pages.add(p);
    }

}