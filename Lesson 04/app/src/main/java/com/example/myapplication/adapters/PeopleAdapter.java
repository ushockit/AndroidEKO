package com.example.myapplication.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Person;

import java.util.List;


public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Person person, int pos);
    }

    private List<Person> people;
    private LayoutInflater inflater;
    private int layout;
    private OnItemClickListener itemClickListener;

    public PeopleAdapter(@NonNull Context context, int resource, @NonNull List<Person> people) {
        this.people = people;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);

        if (context instanceof OnItemClickListener) {
            itemClickListener = (OnItemClickListener) context;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //создаем View
        View view = inflater.inflate(this.layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //получение Person для которого создается View
        final Person person = people.get(position);
        holder.imageView.setImageResource(person.getPicture());
        holder.tvFirstName.setText(person.getFirstName());
        holder.tvLastName.setText(person.getLastName());
        holder.tvBirth.setText(person.getBirth().toString());

        holder.llRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(person, position);
            }
        });

        holder.btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.setFirstName("New first name");
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final LinearLayout llRoot;
        final ImageView imageView;
        final TextView tvFirstName;
        final TextView tvLastName;
        final TextView tvBirth;
        final Button btnChange;

        public ViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.imageViewListItem);
            tvFirstName = view.findViewById(R.id.tvFirstNameListItem);
            tvLastName = view.findViewById(R.id.tvLastNameListItem);
            tvBirth = view.findViewById(R.id.tvBirthListItem);
            llRoot = view.findViewById(R.id.rootPerson);
            btnChange = view.findViewById(R.id.btnChange);

            //tvFirstName.setBackgroundColor(Color.);
        }
    }
}
