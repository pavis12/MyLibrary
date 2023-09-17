package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class book_activity extends AppCompatActivity {

    public static final String BOOK_ID_KEY="bookid";

    private TextView authornametxt,booknametxt,pgnotxt,longdesctxt;
    private Button crbtn,wtrbtn,arbtn,atfbtn;
    private ImageView bookactimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initViews();


        //String ld="Ten-year-old Harry Potter is an orphan who lives in the fictional London suburb of Little Whinging, Surrey, with the Dursleys: his uncaring Aunt Petunia, loathsome Uncle Vernon, and spoiled cousin Dudley. The Dursleys barely tolerate Harry, and Dudley bullies him. One day Harry is astonished to receive a letter addressed to him in the cupboard under the stairs (where he sleeps). Before he can open the letter, however, Uncle Vernon takes it. Letters for Harry subsequently arrive each day, in increasing numbers, but Uncle Vernon tears them all up, and finally, in an attempt to escape the missives, the Dursleys go to a miserable shack on a small island. On Harry’s 11th birthday, a giant named Hagrid arrives and reveals that Harry is a wizard and that he has been accepted at the Hogwarts School of Witchcraft and Wizardry."+"\n"+ "He also sheds light on Harry’s past, informing the boy that his parents, a wizard and a witch, were killed by the evil wizard Voldemort and that Harry acquired the lightning-bolt scar on his forehead during the fatal confrontation.Upon arrival at the school, the students are sorted into one of four houses—Gryffindor, Hufflepuff, Ravenclaw, or Slytherin. Harry ends up in Gryffindor, and during his eventful first year at Hogwarts he becomes close friends with two other members of the house, Ron Weasley, who comes from an old wizarding family, and Hermione Granger, whose parents are Muggles (those who are not magical). Harry also finds that he has an enemy in Draco Malfoy (Slytherin). In addition, Harry’s prowess in flying on a broomstick makes him a star of Gryffindor’s Quidditch team. Hoping to get Harry and his friends into trouble, Draco tricks them into leaving their rooms one night, a violation of school rules. While trying to avoid being caught, they discover a three-headed dog guarding a trapdoor. Harry gradually comes to the conclusion that Professor Snape, who teaches Potions, dislikes him intensely and is trying to get hold of whatever is behind the trapdoor. Harry receives his father’s cloak of invisibility as a Christmas gift, and, while exploring under the cloak’s cover, he finds the Mirror of Erised, in which he can see his parents. Later, headmaster Albus Dumbledore explains that the mirror shows the viewer’s deepest desire.\n" +
               // "\n" +
                //"\nHarry, Ron, and Hermione deduce that the treasure under the trapdoor is the Philosopher’s Stone, which can transform metal into gold and can also confer immortality. They later discover that Voldemort has been killing unicorns in the Forbidden Forest and drinking their blood, another way to achieve immortality. The trio comes to believe that Snape is in league with the evil wizard. After learning that Hagrid revealed the secret way to lull the three-headed dog to sleep to a suspicious stranger, whom they believe to be either Snape or Voldemort, they are certain that the Philosopher’s Stone is in danger. The three classmates use the cloak of invisibility on a secret mission to get the Stone themselves to keep it from Voldemort. After getting past the dog and defeating various protective spells, Harry reaches the room in which the Stone is hidden and is surprised to find the perpetually nervous Professor Quirrell there. Quirrell fails to figure out how to retrieve the Stone from the Mirror of Erised (the final protective measure) and forces Harry to try. When standing in front of the mirror, wishing only to protect the Stone and not use it for himself, Harry feels the Stone’s weight in his pocket but refuses to tell Quirrell that he has it. Quirrell unwraps his turban, revealing Voldemort’s face on the back of his head. Voldemort explains that he has been sharing Quirrell’s body until he can get to the Stone and become fully alive again, and Voldemort/Quirrell and Harry fight for possession of the Stone, until Harry blacks out. He awakens in the infirmary and learns that Dumbledore saved him, the Stone is to be destroyed, and Voldemort escaped.";
        //Book book=new Book(1,"Harry Potter and the Philosopher's Stone","J. K. Rowling",223,"https://static.wikia.nocookie.net/harrypotter/images/f/fb/PS_poster.jpg/revision/latest?cb=20180318153750","An 11-year-old orphan living with his unwelcoming aunt, uncle, and cousin, who learns of his own fame as a wizard known to have survived his parents,murder at the hands of the dark wizard Lord Voldemort as an infant when he is accepted to Hogwarts School of Witchcraft and Wizardry.",ld);


        Intent intent=getIntent();
        if(null!=intent){
            int bookid=intent.getIntExtra(BOOK_ID_KEY,-1);
            if(bookid!=-1){
                Book ib=Utils.getInstance(this).getBookId(bookid);
                if(null!=ib){
                    setData(ib);

                    handlealredyreadbooks(ib);
                    handlewanttoreadbooks(ib);
                    handlecurrentlyreadingbooks(ib);
                    handlefavouritebooks(ib);

                }

            }
        }



    }

    private void handlefavouritebooks(final Book ib) {
        ArrayList<Book> favbooks=Utils.getInstance(this).getFavouritebooks();
        boolean existinfavbooks=false;
        for(Book b:favbooks){
            if(b.getId()== ib.getId()){
                existinfavbooks=true;
            }
        }
        if(existinfavbooks){
            atfbtn.setEnabled(false);
        }
        else{
            atfbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(book_activity.this).addfavbooks(ib)){
                        Toast.makeText(book_activity.this,"Book Added",Toast.LENGTH_SHORT).show();


                        Intent intent=new Intent(book_activity.this, FavBooks.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(book_activity.this,"Sorry something went wrong!!! Try again Later",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handlecurrentlyreadingbooks(final Book ib) {
        ArrayList<Book> crb=Utils.getInstance(this).getCurrentlyreadingbooks();
        boolean existincrb=false;
        for(Book b:crb){
            if(b.getId()== ib.getId()){
                existincrb=true;
            }
        }
        if(existincrb){
            crbtn.setEnabled(false);
        }
        else{
            crbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(book_activity.this).addcrbooks(ib)){
                        Toast.makeText(book_activity.this,"Book Added",Toast.LENGTH_SHORT).show();


                        Intent intent=new Intent(book_activity.this,currentlyReadingbooks.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(book_activity.this,"Sorry something went wrong!!! Try again Later",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    private void handlewanttoreadbooks(final Book ib) {
        ArrayList<Book> wtrb=Utils.getInstance(this).getWanttoraedbooks();
        boolean existinwanttoreadbooks=false;
        for(Book b:wtrb){
            if(b.getId()== ib.getId()){
                existinwanttoreadbooks=true;
            }
        }
        if(existinwanttoreadbooks){
            wtrbtn.setEnabled(false);
        }
        else{
            wtrbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(book_activity.this).addwnattoreadbooks(ib)){
                        Toast.makeText(book_activity.this,"Book Added",Toast.LENGTH_SHORT).show();


                        Intent intent=new Intent(book_activity.this, wantoread.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(book_activity.this,"Sorry something went wrong!!! Try again Later",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     *
     * @param ib
     * to check if the book is alredy resd and if so disable the button already read
     * ib-incomingbook
     */
    private void handlealredyreadbooks(final Book ib) {

        ArrayList<Book> srb=Utils.getInstance(this).getAlreadyreadbooks();
        boolean existinalredyredbooks=false;
        for(Book b:srb){
            if(b.getId()== ib.getId()){
                existinalredyredbooks=true;
            }
        }
        if(existinalredyredbooks){
            arbtn.setEnabled(false);
        }
        else{
            arbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(book_activity.this).addalredyredbooks(ib)){
                        Toast.makeText(book_activity.this,"Book Added",Toast.LENGTH_SHORT).show();


                        Intent intent=new Intent(book_activity.this,alredyreadbooks.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(book_activity.this,"Sorry something went wrong!!! Try again Later",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Book book) {

        booknametxt.setText(book.getName());
        authornametxt.setText(book.getAuthor());
        pgnotxt.setText(String.valueOf(book.getPages()));
        longdesctxt.setText(book.getLongdesc());
        Glide.with(this).asBitmap().load(book.getImageurl()).into(bookactimage);

    }


    private void initViews() {
        bookactimage=findViewById(R.id.bookactimage);
        crbtn=findViewById(R.id.crbtn);
        wtrbtn=findViewById(R.id.wtrbtn);
        arbtn=findViewById(R.id.btnalready);
        atfbtn=findViewById(R.id.btnalready);
        authornametxt=findViewById(R.id.authornametxt);
        booknametxt=findViewById(R.id.booknametxt);
        pgnotxt=findViewById(R.id.pgnotxt);
        longdesctxt=findViewById(R.id.longdesctxt);


    }
}