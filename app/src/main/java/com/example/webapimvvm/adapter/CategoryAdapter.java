package com.example.webapimvvm.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webapimvvm.model.Category;
import com.example.webapimvvm.R;
import com.example.webapimvvm.ui.ProductActivity;
import com.example.webapimvvm.databinding.ItemCategoryBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<Category> categoryArrayList;
    private Context context;
    private int userID;
    private SharedPreferences sharedPreferences;
    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemCategoryBinding itemCategoryBinding;
        public ViewHolder(ItemCategoryBinding itemCategoryBinding) {
            super(itemCategoryBinding.getRoot());
            this.itemCategoryBinding = itemCategoryBinding;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CategoryAdapter(ArrayList<Category> itemLists, Context context) {
        sharedPreferences = context.getSharedPreferences("userNo",MODE_PRIVATE);
        //userID = Integer.parseInt(Encryption.decrypt(sharedPreferences.getString("userNo","GuZMgQ2zRFt6sFV53NLtnA==").toString()));
        userID = 1;
        categoryArrayList = itemLists;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding itemCategoryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_category, parent, false);
        return new ViewHolder(itemCategoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        final Category category = categoryArrayList.get(position);
        holder.itemCategoryBinding.textViewCategoryItem.setText(category.getCategoryName());
        Picasso.get().load(category.getCategoryImage()).fit().centerCrop().into(holder.itemCategoryBinding.imageViewCategoryImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("categoryID",category.getCategoryID());
                intent.putExtra("userID",userID);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }
}
