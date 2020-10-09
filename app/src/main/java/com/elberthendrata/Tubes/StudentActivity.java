package com.elberthendrata.Tubes;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.elberthendrata.Tubes.adapter.StudentRecyclerViewAdapter;
import com.elberthendrata.Tubes.database.DatabaseClient;
import com.elberthendrata.Tubes.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class StudentActivity extends AppCompatActivity {

    private TextInputEditText editText;
    private FloatingActionButton addBtn;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        editText = findViewById(R.id.input_name);
        addBtn = findViewById(R.id.add_member);
        refreshLayout = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.user_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment AddFragment = new AddStudentFragment();

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, AddFragment)
                        .commit();
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUsers();
                refreshLayout.setRefreshing(false);
            }
        });

        getUsers();
    }

    private void getUsers(){
        class GetUsers extends AsyncTask<Void, Void, List<Student>>{

            @Override
            protected List<Student> doInBackground(Void... voids) {
                List<Student> studentList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getDatabase2()
                        .studentDao()
                        .getAll();
                return studentList;
            }

            @Override
            protected void onPostExecute(List<Student> users) {
                super.onPostExecute(users);
                final StudentRecyclerViewAdapter adapter = new StudentRecyclerViewAdapter(StudentActivity.this, users);
                recyclerView.setAdapter(adapter);
                if (users.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Empty List", Toast.LENGTH_SHORT).show();
                }

                SearchView searchView = findViewById(R.id.search);
                searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        adapter.getFilter().filter(s);
                        return false;
                    }
                });


            }
        }

        GetUsers get = new GetUsers();
        get.execute();
    }

}

