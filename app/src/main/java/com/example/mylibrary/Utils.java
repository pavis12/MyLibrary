package com.example.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOKS_KEY="all_books";
    private static final String ALREADY_READ_BOOKS="already_read_books";
    private static final String WANT_TO_READ_BOOKS="want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS="currently_reading_books";
    private static final String FAVOURITE_BOOKS="favourite_books";



    //Utils class is used up instead of a database but this does not stays long when we close our project dataset all goes
    private static Utils instance;

    private SharedPreferences sharedpreferences;

    //private static ArrayList<Book>allbooks;
    //private static ArrayList<Book>currentlyreadingbooks;
    //private static ArrayList<Book>wanttoraedbooks;
    //private static ArrayList<Book>alreadyreadbooks;
    //private static ArrayList<Book>favouritebooks;


    private Utils(Context context) {

        sharedpreferences=context.getSharedPreferences("alternate_db",Context.MODE_PRIVATE);
        Gson gson =new Gson();

        if(null==getAllbooks()){
            initdata();}
        SharedPreferences.Editor editor=sharedpreferences.edit();
        if(null==getCurrentlyreadingbooks()){
            editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
            }
        if(null==getWanttoraedbooks()){
            editor.putString(WANT_TO_READ_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
            }
        if(null==getAlreadyreadbooks()){

            editor.putString(ALREADY_READ_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
            }
        if(null==getFavouritebooks()){
            editor.putString(FAVOURITE_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
            }
    }

    private void initdata() {


        ArrayList<Book> books=new ArrayList<>();

        books.add(new Book(1,"Harry Potter and the Philosopher's Stone","J. K. Rowling",223,"https://static.wikia.nocookie.net/harrypotter/images/f/fb/PS_poster.jpg/revision/latest?cb=20180318153750","An 11-year-old orphan living with his unwelcoming aunt, uncle, and cousin, who learns of his own fame as a wizard known to have survived his parents,murder at the hands of the dark wizard Lord Voldemort as an infant when he is accepted to Hogwarts School of Witchcraft and Wizardry.","long desc"));
        books.add(new Book(2,"Charlie and the Chocolate Factory","Roald Dahl",192,"https://m.media-amazon.com/images/M/MV5BNjcxMjg1Njg2NF5BMl5BanBnXkFtZTcwMjQ4NzMzMw@@._V1_.jpg","Eleven-year-old Charlie Bucket lives in poverty with his parents and grandparents in a town which is home to a world-famous chocolate factory. One day, Charlie's bedridden Grandpa Joe tells him about Willy Wonka, the factory's eccentric owner, and all of his fantastical candies.","long desc"));
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Gson gson =new Gson();
        editor.putString(ALL_BOOKS_KEY,gson.toJson(books));
        editor.commit();
    }


    public static Utils getInstance(Context context) {
         if(null!=instance){
             return instance;
         }
         else{
             instance=new Utils(context);
             return instance;
         }
    }

    public  ArrayList<Book> getAllbooks() {
        Gson gson=new Gson();
        //Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<Book> books=gson.fromJson(sharedpreferences.getString(ALL_BOOKS_KEY,null),new TypeToken<ArrayList<Book>>(){}.getType());

        return books;
    }

    public  ArrayList<Book> getCurrentlyreadingbooks() {
        Gson gson=new Gson();
        //Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<Book> books=gson.fromJson(sharedpreferences.getString(CURRENTLY_READING_BOOKS,null),new TypeToken<ArrayList<Book>>(){}.getType());

        return books;
    }

    public  ArrayList<Book> getWanttoraedbooks() {
        Gson gson=new Gson();
        //Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<Book> books=gson.fromJson(sharedpreferences.getString(WANT_TO_READ_BOOKS,null),new TypeToken<ArrayList<Book>>(){}.getType());

        return books;
    }

    public  ArrayList<Book> getAlreadyreadbooks() {

        Gson gson=new Gson();
        //Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<Book> books=gson.fromJson(sharedpreferences.getString(ALREADY_READ_BOOKS,null),new TypeToken<ArrayList<Book>>(){}.getType());

        return books;
    }

    public  ArrayList<Book> getFavouritebooks() {
        Gson gson=new Gson();
        //Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<Book> books=gson.fromJson(sharedpreferences.getString(FAVOURITE_BOOKS,null),new TypeToken<ArrayList<Book>>(){}.getType());

        return books;
    }

    public Book getBookId(int id){
        ArrayList<Book>books=getAllbooks();
        if(null!=books){
            for(Book b:books){
                if(b.getId()==id){
                    return b;
                }
            }
        }
        return null;
    }

    public boolean addalredyredbooks(Book book){

        ArrayList<Book>books=getAlreadyreadbooks();
        if(null!=books){
            if(books.add(book)){
                Gson gson=new Gson();
                SharedPreferences.Editor editor=sharedpreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addwnattoreadbooks(Book book){
        ArrayList<Book>books=getWanttoraedbooks();
        if(null!=books){
            if(books.add(book)){
                Gson gson=new Gson();
                SharedPreferences.Editor editor=sharedpreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean addfavbooks(Book book){
        ArrayList<Book>books=getAlreadyreadbooks();
        if(null!=books){
            if(books.add(book)){
                Gson gson=new Gson();
                SharedPreferences.Editor editor=sharedpreferences.edit();
                editor.remove(FAVOURITE_BOOKS);
                editor.putString(FAVOURITE_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addcrbooks(Book book){
        ArrayList<Book>books=getCurrentlyreadingbooks();
        if(null!=books){
            if(books.add(book)){
                Gson gson=new Gson();
                SharedPreferences.Editor editor=sharedpreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean removefromalredyread(Book book){

        ArrayList<Book> books=getAlreadyreadbooks();
        if(null!=books){
            for(Book b:books){
                if(b.getId()==book.getId()){
                    Gson gson=new Gson();
                    SharedPreferences.Editor editor=sharedpreferences.edit();
                    editor.remove(ALREADY_READ_BOOKS);
                    editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                    editor.commit();
                    return true;

                }
            }
        }
        return false;
    }
    public boolean removefromfav(Book book){
        ArrayList<Book> books=getFavouritebooks();
        if(null!=books){
            for(Book b:books){
                if(b.getId()==book.getId()){
                    Gson gson=new Gson();
                    SharedPreferences.Editor editor=sharedpreferences.edit();
                    editor.remove(FAVOURITE_BOOKS);
                    editor.putString(FAVOURITE_BOOKS, gson.toJson(books));
                    editor.commit();
                    return true;

                }
            }
        }
        return false;
    }
    public boolean removefromwanttoread(Book book){
        ArrayList<Book> books=getWanttoraedbooks();
        if(null!=books){
            for(Book b:books){
                if(b.getId()==book.getId()){
                    Gson gson=new Gson();
                    SharedPreferences.Editor editor=sharedpreferences.edit();
                    editor.remove(WANT_TO_READ_BOOKS);
                    editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                    editor.commit();
                    return true;

                }
            }
        }
        return false;
    }
    public boolean removefromcurrentlyreading(Book book){
        ArrayList<Book> books=getCurrentlyreadingbooks();
        if(null!=books){
            for(Book b:books){
                if(b.getId()==book.getId()){
                    Gson gson=new Gson();
                    SharedPreferences.Editor editor=sharedpreferences.edit();
                    editor.remove(CURRENTLY_READING_BOOKS);
                    editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                    editor.commit();
                    return true;

                }
            }
        }
        return false;
    }




}
