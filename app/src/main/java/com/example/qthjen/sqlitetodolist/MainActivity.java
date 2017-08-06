package com.example.qthjen.sqlitetodolist;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DataBase dataBase;
    ArrayList<Work> listWork;
    ListView lv;
    CustomWork customWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);

        listWork = new ArrayList<Work>();
        customWork = new CustomWork(MainActivity.this, R.layout.cutsom_list, listWork);
        lv.setAdapter(customWork);

        /** tạo cơ sở dữ liệu **/
        dataBase = new DataBase(this, "ghichu.sqlite", null, 1);

        /** tạo bảng CongViec **/
        dataBase.QueryData("CREATE TABLE IF NOT EXISTS CongViec(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenCV VARCHAR)");

        /** insert data **/
     //   dataBase.QueryData("INSERT INTO CongViec VALUES(null, 'Thực hành viết app android')");

        /** đọc dữ liệu bằng cursor **/
        ReadData();

    }

    private void ReadData() {

        Cursor dataCongViec = dataBase.GetData("SELECT * FROM CongViec");
        listWork.clear();
        while ( dataCongViec.moveToNext()) {
            String name = dataCongViec.getString(1);
            int id = dataCongViec.getInt(0);
            listWork.add(new Work(id, name));
        }
        customWork.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if ( item.getItemId() == R.id.mnAdd) {
            DialogTheme();
        }

        return super.onOptionsItemSelected(item);
    }

    private void DialogTheme() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.activity_dialog);

        final EditText etInput = (EditText) dialog.findViewById(R.id.etInput);
        Button btCancel = (Button) dialog.findViewById(R.id.btCancel);
        Button btAdd = (Button) dialog.findViewById(R.id.btAdd);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameWork = etInput.getText().toString();
                if ( nameWork.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter data", Toast.LENGTH_SHORT).show();
                } else {
                    dataBase.QueryData("INSERT INTO CongViec VALUES(null, '"+ nameWork + "')");
                    dialog.dismiss();
                }
                ReadData();

            }
        });

        dialog.show();

    }

    public void EditDialog(String nameWork, final int idWork) {

        final Dialog dialog = new Dialog(MainActivity.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.edit_dialog);

        final EditText etEditEnter = (EditText) dialog.findViewById(R.id.etEditEnter);
        Button btConfirm = (Button) dialog.findViewById(R.id.btConfirm);
        Button btEtCancel = (Button) dialog.findViewById(R.id.btEtCancel);

        etEditEnter.setText(nameWork);
        etEditEnter.requestFocus();

        btEtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameUpdate = etEditEnter.getText().toString().trim();
                /** update database **/
                dataBase.QueryData("UPDATE CongViec SET TenCV = '"+ nameUpdate +"' WHERE Id = '"+ idWork +"'");
                dialog.dismiss();
                ReadData();

            }
        });

        dialog.show();

    }

    public void DialogDelete(String nameWork, final int idWork) {

        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(this);
        dialogDelete.setMessage("Do you want to delete " + nameWork + "?");

        dialogDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                /** delete database **/
                dataBase.QueryData("DELETE FROM CongViec WHERE Id = '" + idWork + "'");
                ReadData();
            }
        });

        dialogDelete.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });
        dialogDelete.show();

    }


}
