package com.example.libraryapplicationcoursework.ui.bookInformation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libraryapplicationcoursework.R;
import com.example.libraryapplicationcoursework.adapter.BoughtBookListAdapter;
import com.example.libraryapplicationcoursework.adapter.FavoriteBookListAdapter;
import com.example.libraryapplicationcoursework.database.AppDatabase;
import com.example.libraryapplicationcoursework.entity.BoughtBook;
import com.example.libraryapplicationcoursework.entity.FavoriteBook;
import com.example.libraryapplicationcoursework.entity.User;
import com.example.libraryapplicationcoursework.helper.GeneralData;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookInformationFragment extends Fragment {

    private TextView tv_title;
    private TextView tv_author;
    private TextView tv_genre;
    private TextView tv_description;
    private ImageView iv_book;
    private TextView tv_price;
    private ImageView iv_heart;
    private Button btn_buy;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_information,container,false);

        AppDatabase database = AppDatabase.getInstance(getContext());

        tv_title = (TextView) view.findViewById(R.id.bookTitleInf);
        tv_author = (TextView) view.findViewById(R.id.bookAuthorInf);
        tv_price = (TextView) view.findViewById(R.id.bookPriceInf);
        tv_genre = (TextView) view.findViewById(R.id.bookGenreInf);
        tv_description = (TextView) view.findViewById(R.id.bookDescriptionInf);
        iv_book = (ImageView) view.findViewById(R.id.bookImageInf);
        iv_heart = (ImageView) view.findViewById(R.id.heartInf);
        btn_buy = (Button) view.findViewById(R.id.buyButton);


        String title = getArguments().getString("Title","Title");
        String author = getArguments().getString("Author","Author");
        String genre = getArguments().getString("Genre","Genre");
        String description = getArguments().getString("Description","Description");
        int image = getArguments().getInt("Image",R.drawable.club);
        int price = getArguments().getInt("Price",200);
        String username = GeneralData.getLoggedUser().getUsername();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String curDate = formatter.format(date);
        BoughtBook book = new BoughtBook(title,username,curDate,image);



        iv_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_heart.setImageResource(R.drawable.heartred);
                database.bookDAO().addFavoriteBook(title,image,username);
                FavoriteBookListAdapter adapter = new FavoriteBookListAdapter();
                adapter.setBooks(database.bookDAO().getAllFavoriteBooksByUsername(username));
            }
        });

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = database.userDAO().checkUsersWallet(username,price);
                if(user!=null){
                    database.bookDAO().buyBook(book);
                    BoughtBookListAdapter adapter = new BoughtBookListAdapter();


                    adapter.setBooks(database.bookDAO().getAllBoughtBooksByUsername(username));
                    database.userDAO().subtractMoney(username,price);
                    Toast.makeText(getContext(),"Вы успешно купили книгу '" + title + "'",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext(),"У вас недостаточно денег!",Toast.LENGTH_LONG).show();
                }
            }
        });

        tv_title.setText(title);
        tv_description.setText(description);
        tv_genre.setText(genre);
        tv_author.setText(author);
        iv_book.setImageResource(image);
        tv_price.setText("$" + price);

        return view;
    }
}