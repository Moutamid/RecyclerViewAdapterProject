package dev.moutamid.recyclerviewadapterproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MovieModel> moviesArrayList = new ArrayList<>();

    private enum LayoutType {
        LIST, GRID
    }

    LayoutType layoutType;

    private RecyclerView movieRecyclerView;
    private RecyclerViewAdapterMessages adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        layoutType = LayoutType.LIST;

        fillUpArrayList();

        initRecyclerView(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list_option:
                layoutType = LayoutType.LIST;

                initRecyclerView(true);

                return true;
            case R.id.grid_option:
                layoutType = LayoutType.GRID;

                initRecyclerView(false);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
//        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_option_menu, menu);
        return true;
//        return super.onCreateOptionsMenu(menu);
    }

    private void initRecyclerView(boolean isList) {

        movieRecyclerView = findViewById(R.id.recyclerview);
        movieRecyclerView.addItemDecoration(new DividerItemDecoration(movieRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        adapter = new RecyclerViewAdapterMessages();

//        LinearLayoutManager layoutManagerUserFriends = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

//        int mNoOfColumns = 2;
        //---------------------
        if (!isList) {
            int mNoOfColumns = calculateNoOfColumns(160);
            movieRecyclerView.setLayoutManager(new GridLayoutManager(this, mNoOfColumns));
        }

        //---------------------
        if (isList)
            movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        movieRecyclerView.setHasFixedSize(true);
        movieRecyclerView.setNestedScrollingEnabled(false);

        movieRecyclerView.setAdapter(adapter);

    }


    private int calculateNoOfColumns(float columnWidthDp) { // For example columnWidthdp=180
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5); // +0.5 for correct rounding to int.
        return noOfColumns;
    }


    private class RecyclerViewAdapterMessages extends RecyclerView.Adapter
            <RecyclerViewAdapterMessages.ViewHolderRightMessage> {

        @NonNull
        @Override
        public ViewHolderRightMessage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_recyclerview, parent, false);
            return new ViewHolderRightMessage(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolderRightMessage holder, int positio1n) {

            int position = holder.getAdapterPosition();

            MovieModel model = moviesArrayList.get(position);

            if (layoutType == LayoutType.LIST) {

                holder.parentLayout.setVisibility(View.VISIBLE);

            } else {
                // LAYOUT TYPE IS GRID
                holder.parentLayout1.setVisibility(View.VISIBLE);
            }

            holder.imageView.setImageResource(model.getImageId());
            holder.movieName.setText(model.getMovieName());
            holder.directorName.setText(model.getDirectorName());

            holder.imageView1.setImageResource(model.getImageId());
            holder.movieName1.setText(model.getMovieName());
            holder.directorName1.setText(model.getDirectorName());

            holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(model.getVideoUrl())));

                }
            });

            holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    final CharSequence[] items = {"View video clip", "View movie's wikipedia", "View director's wikipedia"};
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int position) {
                            dialog.dismiss();
                            switch (position) {
                                case 0:
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(model.getVideoUrl())));
                                    break;
                                case 1:
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(model.getVideoWikiUrl())));
                                    break;
                                case 2:
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(model.getDirectorWikiUrl())));
                                    break;
                            }

                        }
                    });

                    dialog = builder.create();
                    dialog.show();

                    return false;
                }
            });

            holder.parentLayout1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(model.getVideoUrl())));

                }
            });

            holder.parentLayout1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    final CharSequence[] items = {"View video clip", "View movie's wikipedia", "View director's wikipedia"};
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int position) {
                            dialog.dismiss();
                            switch (position) {
                                case 0:
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(model.getVideoUrl())));
                                    break;
                                case 1:
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(model.getVideoWikiUrl())));
                                    break;
                                case 2:
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(model.getDirectorWikiUrl())));
                                    break;
                            }

                        }
                    });

                    dialog = builder.create();
                    dialog.show();

                    return false;
                }
            });

        }

        @Override
        public int getItemCount() {
            if (moviesArrayList == null)
                return 0;
            return moviesArrayList.size();
        }

        public class ViewHolderRightMessage extends RecyclerView.ViewHolder {

            TextView movieName, directorName;
            ImageView imageView;
            RelativeLayout parentLayout;

            TextView movieName1, directorName1;
            ImageView imageView1;
            LinearLayout parentLayout1;

            public ViewHolderRightMessage(@NonNull View v) {
                super(v);
                movieName = v.findViewById(R.id.movieName);
                directorName = v.findViewById(R.id.directorName);
                imageView = v.findViewById(R.id.imageView);
                parentLayout = v.findViewById(R.id.parentLayout);

                movieName1 = v.findViewById(R.id.movieName1);
                directorName1 = v.findViewById(R.id.directorName1);
                imageView1 = v.findViewById(R.id.imageView1);
                parentLayout1 = v.findViewById(R.id.gridItemParentLayout);

            }
        }

    }

    private void fillUpArrayList() {

        MovieModel model;
        model = new MovieModel();

//        for (int i = 0; i <= 10; i++) {

        model.setMovieName("Triangle");
        model.setDirectorName("Christopher Smith");
        model.setVideoUrl("https://youtu.be/17XqBdCiHOI");
        model.setDirectorWikiUrl("https://en.wikipedia.org/wiki/Christopher_Smith_(director)");
        model.setVideoWikiUrl("https://en.wikipedia.org/wiki/Triangle_(2009_British_film)");
        model.setImageId(R.drawable.img);
        moviesArrayList.add(model);
//        }
        model = new MovieModel();

        model.setMovieName("The Godfather");
        model.setDirectorName("Francis Ford Coppola");
        model.setVideoUrl("https://www.youtube.com/watch?v=sY1S34973zA");
        model.setDirectorWikiUrl("https://en.wikipedia.org/wiki/Francis_Ford_Coppola");
        model.setVideoWikiUrl("https://en.wikipedia.org/wiki/The_Godfather");
        model.setImageId(R.drawable.img_1);
        moviesArrayList.add(model);

        model = new MovieModel();

        model.setMovieName("The Wizard of Oz");
        model.setDirectorName("Victor Fleming");
        model.setVideoUrl("https://www.youtube.com/watch?v=H_3T4DGw10U");
        model.setDirectorWikiUrl("https://en.wikipedia.org/wiki/Victor_Fleming");
        model.setVideoWikiUrl("https://en.wikipedia.org/wiki/The_Wizard_of_Oz_(1939_film)");
        model.setImageId(R.drawable.img_2);
        moviesArrayList.add(model);

        model = new MovieModel();

        model.setMovieName("Star Wars");
        model.setDirectorName("George Lucas");
        model.setVideoUrl("https://www.youtube.com/watch?v=8Qn_spdM5Zg");
        model.setDirectorWikiUrl("https://en.wikipedia.org/wiki/George_Lucas");
        model.setVideoWikiUrl("https://en.wikipedia.org/wiki/Star_Wars");
        model.setImageId(R.drawable.img_3);
        moviesArrayList.add(model);

        model = new MovieModel();

        model.setMovieName("Back to the Future");
        model.setDirectorName("Robert Zemeckis");
        model.setVideoUrl("https://www.youtube.com/watch?v=qvsgGtivCgs");
        model.setDirectorWikiUrl("https://en.wikipedia.org/wiki/Robert_Zemeckis");
        model.setVideoWikiUrl("https://en.wikipedia.org/wiki/Back_to_the_Future");
        model.setImageId(R.drawable.img_4);
        moviesArrayList.add(model);

        model = new MovieModel();

        model.setMovieName("To Kill a Mockingbird");
        model.setVideoUrl("https://www.youtube.com/watch?v=KR7loA_oziY");
        model.setVideoWikiUrl("https://en.wikipedia.org/wiki/To_Kill_a_Mockingbird");
        model.setDirectorName("Robert Mulligan");
        model.setDirectorWikiUrl("https://en.wikipedia.org/wiki/Robert_Mulligan");
        model.setImageId(R.drawable.img_5);
        moviesArrayList.add(model);

        model = new MovieModel();

        model.setMovieName("Apocalypse Now");
        model.setVideoUrl("https://www.youtube.com/watch?v=9l-ViOOFH-s");
        model.setVideoWikiUrl("https://en.wikipedia.org/wiki/Apocalypse_Now");
        model.setDirectorName("Francis Ford Coppola");
        model.setDirectorWikiUrl("https://en.wikipedia.org/wiki/Francis_Ford_Coppola");
        model.setImageId(R.drawable.img_6);
        moviesArrayList.add(model);

        model = new MovieModel();

        model.setMovieName("Annie Hall");
        model.setVideoUrl("https://www.youtube.com/watch?v=OqVgCfZX-yE");
        model.setVideoWikiUrl("https://en.wikipedia.org/wiki/Annie_Hall");
        model.setDirectorName("Woody Allen");
        model.setDirectorWikiUrl("https://en.wikipedia.org/wiki/Woody_Allen");
        model.setImageId(R.drawable.img_7);
        moviesArrayList.add(model);

        model = new MovieModel();

        model.setMovieName("Goodfellas");
        model.setVideoUrl("https://www.youtube.com/watch?v=qo5jJpHtI1Y");
        model.setVideoWikiUrl("https://en.wikipedia.org/wiki/Goodfellas");
        model.setDirectorName("Martin Scorsese");
        model.setDirectorWikiUrl("https://en.wikipedia.org/wiki/Martin_Scorsese");
        model.setImageId(R.drawable.img_8);
        moviesArrayList.add(model);

        model = new MovieModel();

        model.setMovieName("The Silence of the Lambs");
        model.setVideoUrl("https://www.youtube.com/watch?v=W6Mm8Sbe__o");
        model.setVideoWikiUrl("https://en.wikipedia.org/wiki/The_Silence_of_the_Lambs_(film)");
        model.setDirectorName("Jonathan Demme");
        model.setDirectorWikiUrl("https://en.wikipedia.org/wiki/Jonathan_Demme");
        model.setImageId(R.drawable.img_9);
        moviesArrayList.add(model);

        model = new MovieModel();

        model.setMovieName("Blade Runner");
        model.setVideoUrl("https://www.youtube.com/watch?v=gCcx85zbxz4");
        model.setVideoWikiUrl("https://en.wikipedia.org/wiki/Blade_Runner");
        model.setDirectorName("Ridley Scott");
        model.setDirectorWikiUrl("https://en.wikipedia.org/wiki/Ridley_Scott");
        model.setImageId(R.drawable.img_10);
        moviesArrayList.add(model);
    }
}