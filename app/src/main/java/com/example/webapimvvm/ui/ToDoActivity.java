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
import com.example.webapimvvm.adapter.ToDoAdapter;
import com.example.webapimvvm.viewmodel.TodoActivityViewModel;
import com.example.webapimvvm.databinding.ActivityToDoBinding;

import java.util.ArrayList;

public class ToDoActivity extends AppCompatActivity {
    private ActivityToDoBinding activityToDoBinding;
    private TodoActivityViewModel todoActivityViewModel;

    private ArrayList<ToDo> arrayListToDo;
    private ToDoAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        activityToDoBinding = DataBindingUtil.setContentView(this,R.layout.activity_to_do);
        activityToDoBinding.setTodoActivityVeriable(this);
        todoActivityViewModel = new ViewModelProvider(this).get(TodoActivityViewModel.class);

        userID = getIntent().getIntExtra("userID",0);

        activityToDoBinding.recyclerViewToDoList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(ToDoActivity.this);
        activityToDoBinding.recyclerViewToDoList.setLayoutManager(layoutManager);
        arrayListToDo = new ArrayList<>();

        getList();

    }
    public void getList(){
        todoActivityViewModel.getTodos().observe(this, new Observer<ArrayList<ToDo>>() {
            @Override
            public void onChanged(ArrayList<ToDo> toDos) {
                if(toDos != null){
                    arrayListToDo = toDos;
                    adapter = new ToDoAdapter(arrayListToDo,ToDoActivity.this);
                    activityToDoBinding.recyclerViewToDoList.setAdapter(adapter);

                }else{

                }
            }
        });

        todoActivityViewModel.getTodoList(userID,0);
    }

}