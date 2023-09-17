package com.example.mylibrary;

import static com.example.mylibrary.book_activity.BOOK_ID_KEY;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class sabRecyclerViewAdapter extends RecyclerView.Adapter<sabRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "sabRecyclerViewAdapter";



    private ArrayList<Book>books =new ArrayList<>();
    private Context mContext;

    private String parentActivity;

    public sabRecyclerViewAdapter(Context mContext,String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items_books,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position ) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.txtname.setText(books.get(position).getName());
        Glide.with(mContext)
                .asBitmap()
                .load(books.get(position).getImageurl())
                .into(holder.imgbook);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,book_activity.class);
                intent.putExtra(BOOK_ID_KEY,books.get(position).getId());
                mContext.startActivity(intent);
            }
        });

        holder.txtauthor.setText(books.get(position).getAuthor());

        holder.txtshortdesc.setText(books.get(position).getShortdesc());
        if(books.get(position).isExpanded()){
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelativelayout.setVisibility(View.VISIBLE);
            holder.downarrow.setVisibility(View.GONE);

            if(parentActivity.equals("allbooks")){
                holder.btndelete.setVisibility(View.GONE);
            }
            else if(parentActivity.equals("alreadyread")){
                holder.btndelete.setVisibility(View.VISIBLE);
                holder.btndelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder =new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete"+books.get(position).getName()+"?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance(mContext).removefromalredyread(books.get(position))){
                                    Toast.makeText(mContext,"Book Removed",Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();

                    }
                });

            }
            else if(parentActivity.equals("wanttoread")){
                holder.btndelete.setVisibility(View.VISIBLE);
                holder.btndelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder =new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete"+books.get(position).getName()+"?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance(mContext).removefromwanttoread(books.get(position))){
                                    Toast.makeText(mContext,"Book Removed",Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();

                    }
                });

            }
            else if(parentActivity.equals("currentlyreading")){
                holder.btndelete.setVisibility(View.VISIBLE);
                holder.btndelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder =new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete"+books.get(position).getName()+"?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance(mContext).removefromcurrentlyreading(books.get(position))){
                                    Toast.makeText(mContext,"Book Removed",Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();

                    }
                });

            }
            else if(parentActivity.equals("favbooks")){
                holder.btndelete.setVisibility(View.VISIBLE);
                holder.btndelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder =new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete"+books.get(position).getName()+"?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance(mContext).removefromfav(books.get(position))){
                                    Toast.makeText(mContext,"Book Removed",Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();

                    }
                });

            }
        }
        else{
            holder.expandedRelativelayout.setVisibility(View.GONE);
            holder.downarrow.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private ImageView imgbook;
        private TextView txtname;

        private ImageView uparrow,downarrow;
        private RelativeLayout expandedRelativelayout;
        private TextView txtauthor,txtshortdesc;

        private TextView btndelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.parent);
            imgbook=itemView.findViewById(R.id.imgbook);
            txtname=itemView.findViewById(R.id.txtname);
            uparrow=itemView.findViewById(R.id.btnuparrow);
            downarrow=itemView.findViewById(R.id.btndownarrow);
            expandedRelativelayout=itemView.findViewById(R.id.expandedrellayout);
            txtauthor=itemView.findViewById(R.id.authorname);
            txtshortdesc=itemView.findViewById(R.id.txtshortdes);
            btndelete=itemView.findViewById(R.id.btndelete);

            downarrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book=books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });

            uparrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book=books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });

        }
    }
}
