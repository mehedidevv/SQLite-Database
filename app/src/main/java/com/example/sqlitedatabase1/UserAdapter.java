package com.example.sqlitedatabase1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewHolder> {

    //Create List With Constructor.
    List <User> users;

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Class Connection
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_design_user,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.viewHolder holder, int position) {

        User user=users.get(position);
        holder.idTV.setText(String.valueOf(user.getId()));
        holder.nameTV.setText(user.getName());
        holder.ageTV.setText(user.getAge());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView idTV,nameTV,ageTV;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            idTV=itemView.findViewById(R.id.idTV);
            nameTV=itemView.findViewById(R.id.nameTV);
            ageTV=itemView.findViewById(R.id.ageTV);
        }
    }
}
