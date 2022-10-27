package com.example.webapimvvm.adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webapimvvm.model.ToDo;
import com.example.webapimvvm.R;
import com.example.webapimvvm.ui.ToDoActivity;
import com.example.webapimvvm.ui.ToDoListDetailActivity;
import com.example.webapimvvm.viewmodel.TodoActivityViewModel;
import com.example.webapimvvm.databinding.ItemTodoBinding;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
    private ArrayList<ToDo> todoArrayList;
    private Context context;
    private TodoActivityViewModel todoActivityViewModel;
    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemTodoBinding itemTodoBinding;
        public ViewHolder(ItemTodoBinding itemTodoBinding) {
            super(itemTodoBinding.getRoot());
            this.itemTodoBinding = itemTodoBinding;
        }
    }

    public ToDoAdapter(ArrayList<ToDo> itemLists, Context context) {
        todoArrayList = itemLists;
        this.context = context;
    }


    @NonNull
    @Override
    public ToDoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        todoActivityViewModel = new TodoActivityViewModel();
        ItemTodoBinding itemTodoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_todo, parent, false);
        return new ViewHolder(itemTodoBinding);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ToDoAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final ToDo toDo = todoArrayList.get(position);
        holder.itemTodoBinding.textViewToDoNumber.setText("Liste "+String.valueOf(toDo.getTodoID()));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.getDefault());
        LocalDateTime localDateTime = LocalDateTime.parse(toDo.getTodoSaveDate().substring(0,16), dateTimeFormatter);

        String date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(localDateTime);
        holder.itemTodoBinding.textViewToDoDate.setText(String.valueOf(date));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ToDoListDetailActivity.class);
                intent.putExtra("shoppingCartID",toDo.getTodoShoppingCartID());
                context.startActivity(intent);

            }
        });

        holder.itemTodoBinding.imageButtonToDoComplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todoArrayList.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
                toDo.setTodoState(1);
                todoActivityViewModel.updateToDo(toDo.getTodoID(),toDo);
                View rootView = ((ToDoActivity)context).getWindow().getDecorView().findViewById(android.R.id.content);
                Snackbar.make(rootView,"Liste tamamlandı",Snackbar.LENGTH_SHORT).show();

            }
        });
        holder.itemTodoBinding.imageButtonToDoDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Liste siliniyor");
                builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        todoActivityViewModel.deleteTodo(toDo.getTodoShoppingCartID());
                        ((ToDoActivity)context).getList();
                        View rootView = ((ToDoActivity)context).getWindow().getDecorView().findViewById(android.R.id.content);
                        Snackbar.make(rootView,"Liste silindi",Snackbar.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoArrayList.size();
    }
}
