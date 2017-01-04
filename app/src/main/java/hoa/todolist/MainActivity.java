package hoa.todolist;
/*********************************************
 * Course: COMP3074
 * Name: Hoa Nguyen
 * Student ID: 100959069
 * ********************************************/

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBHelper helper;
    private ArrayAdapter<String> mAdapter;
    private ListView mTaskListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DBHelper(this);
        mTaskListView = (ListView)findViewById(R.id.list_todo);
        updateUI();
    }

    //inflate menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.add_item:
                Intent j = new Intent(this, AddItem.class);
                startActivity(j);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //display all title(task) from list table on screen
    private void updateUI(){
        ArrayList<String> taskList = new ArrayList<String>();
        taskList = helper.getAllList();
        if(mAdapter==null){
            mAdapter = new ArrayAdapter<String>(this, R.layout.todo_item,R.id.task_title, taskList);
            mTaskListView.setAdapter(mAdapter);
        }
        else
        {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
    }
    //delete the selected title(task)
    public void deleteTask(View view){
        View parent = (View)view.getParent();
        TextView taskTextView = (TextView)parent.findViewById(R.id.task_title);
        String task = String.valueOf(taskTextView.getText());
        helper.deleteList(task);
        updateUI();
    }
}
