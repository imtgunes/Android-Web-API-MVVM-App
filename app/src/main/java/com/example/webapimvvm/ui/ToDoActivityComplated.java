package com.example.webapimvvm.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.webapimvvm.R;
import com.example.webapimvvm.model.ToDo;
import com.example.webapimvvm.adapter.ToDoComplatedAdapter;
import com.example.webapimvvm.viewmodel.TodoActivityViewModel;
import com.example.webapimvvm.databinding.ActivityToDoComplatedBinding;

import java.util.ArrayList;

public class ToDoActivityComplated extends AppCompatActivity {
    private ActivityToDoComplatedBinding activityToDoComplatedBinding;
    private TodoActivityViewModel todoActivityViewModel;
    private ArrayList<ToDo> arrayListToDoComplated;
    private ToDoComplatedAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_complated);

        activityToDoComplatedBinding = DataBindingUtil.setContentView(this,R.layout.activity_to_do_complated);
        activityToDoComplatedBinding.setTodoComplatedActivityVeriable(this);
        todoActivityViewModel = new ViewModelProvider(this).get(TodoActivityViewModel.class);

        userID = getIntent().getIntExtra("userID",0);

        activityToDoComplatedBinding.recyclerViewTodoComplated.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(ToDoActivityComplated.this);
        activityToDoComplatedBinding.recyclerViewTodoComplated.setLayoutManager(layoutManager);
        arrayListToDoComplated = new ArrayList<>();
        getListComplated();
    }

    public void getListComplated(){
        todoActivityViewModel.getTodos().observe(this, new Observer<ArrayList<ToDo>>() {
            @Override
            public void onChanged(ArrayList<ToDo> toDos) {
                if(toDos != null){
                    arrayListToDoComplated = toDos;
                    adapter = new ToDoComplatedAdapter(arrayListToDoComplated,ToDoActivityComplated.this);
                    activityToDoComplatedBinding.recyclerViewTodoComplated.setAdapter(adapter);

                }else{

                }
            }
        });

        todoActivityViewModel.getTodoList(userID,1);
    }
}