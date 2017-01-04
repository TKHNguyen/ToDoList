package hoa.todolist;
/*********************************************
 * Course: COMP3074
 * Name: Hoa Nguyen
 * Student ID: 100959069
* ********************************************/

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by hoa on 2016-11-11.
 */

public class AddItem extends Activity implements OnClickListener {

    EditText itemText;
    Button addButton, cancelButton;
    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        helper = new DBHelper(this);
        itemText = (EditText)findViewById(R.id.addItem);
        addButton= (Button)findViewById(R.id.add);
        cancelButton= (Button)findViewById(R.id.cancel);
        addButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //return to main activity
            case R.id.cancel:
                Intent j = new Intent(this, MainActivity.class);
                startActivity(j);
                break;

            //add new title into list table
            //then return to main activity
            case R.id.add:
                boolean result = false;
                if(!itemText.getText().toString().equals(""))
                    result = helper.insertList(itemText.getText().toString());
                if(result){
                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(this, "An error has occurred while inserting", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
