package com.example.libraryapplicationcoursework.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryapplicationcoursework.R;
import com.example.libraryapplicationcoursework.adapter.BookListAdapter;
import com.example.libraryapplicationcoursework.database.AppDatabase;
import com.example.libraryapplicationcoursework.databinding.FragmentHomeBinding;
import com.example.libraryapplicationcoursework.entity.Book;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView rv_books;
    private BookListAdapter adapter;
    private TextView tv_title;
    private TextView tv_author;
    private TextView tv_genre;
    private ImageView iv_book;

    List<Book> books;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tv_title = binding.titleMenu;
        tv_author = binding.authorMenu;
        tv_genre = binding.genreMenu;
        iv_book = binding.bookImageMenu;

        AppDatabase database = AppDatabase.getInstance(getContext());
        database.bookDAO().deleteAllBooks();

        initList();
        enterRandomData();

        rv_books = binding.recyclerViewBooks;
        rv_books.setLayoutManager(new GridLayoutManager(getContext(),3));
        adapter = new BookListAdapter(getContext(),books);
        rv_books.setAdapter(adapter);

        return root;
    }

    void enterRandomData(){
        AppDatabase database = AppDatabase.getInstance(getContext());
        Book randomBook = database.bookDAO().randomBook();
        tv_title.setText(randomBook.getTitle());
        tv_author.setText(randomBook.getAuthor());
        tv_genre.setText(randomBook.getGenre());
        iv_book.setImageResource(randomBook.getImage());
    }

    private void initList(){
        AppDatabase database = AppDatabase.getInstance(getContext());
        database.bookDAO().addBook(new Book(
                "451 по Фаренгейту",
                "Рэй Бредберри",
                "Роман «451 градус по Фаренгейту» рассказывает о тоталитарном обществе, в котором литература находится под запретом, а пожарные должны сжигать все запрещённые книги, которые обнаружат, вместе с жилищами владельцев. Владельцы книг при этом, по-видимому, подлежат аресту. Автор изобразил людей, потерявших связь с природой, с интеллектуальным наследием человечества, друг с другом. Люди спешат на работу или с работы, никогда не говоря о том, что они думают или чувствуют, разглагольствуя лишь о бессмысленном и пустом и восторгаются только материальными ценностями. Дома они окружают себя интерактивным телевидением, проецирующимся сразу на все стены и заполняют своё свободное время просмотром телевизионных передач, например, «Родственниками» — бесконечным и бестолковым сериалом. Однако «благополучное», на первый взгляд, государство находится на пороге тотальной разрушительной войны.",
                "Фантастика",
                R.drawable.farengeit,
                120));
        database.bookDAO().addBook(new Book(
                "1984",
                "Джордж Оруэлл",
                "Главный герой — Уинстон Смит — живёт в Лондоне, работает в министерстве правды и является членом внешней партии. Он не разделяет партийные лозунги и идеологию и в глубине души сильно сомневается в партии, окружающей действительности и вообще во всём том, в чём только можно сомневаться. Чтобы «выпустить пар» и не сделать безрассудный поступок, он покупает дневник, в котором старается излагать все свои сомнения. На людях же он старается притворяться приверженцем партийных идей. Однако он опасается, что девушка Джулия, работающая в том же министерстве, шпионит за ним и хочет разоблачить его. В то же время он полагает, что высокопоставленный сотрудник их министерства, член внутренней партии некий О’Брайен также не разделяет мнения партии и является подпольным революционером.",
                "Антиутопия,Фантастика",
                R.drawable.onenineeightfour,
                250
        ));
        database.bookDAO().addBook(new Book(
                "1900-й. Легенда о пианисте",
                "Алессандро Барикко",
                "Алессандро Барикко - один из самых ярких европейских писателей XXI века, автор обошедших весь мир бестселлеров Шелк и Море-океан, лауреат многих престижных литературных премий. Его книга 1900-й. Легенда о пианисте не только выдержала множество переизданий и была переведена на десятки языков, но и легла в основу знаменитого фильма Джузеппе Торнаторе Легенда о пианисте с Тимом Ротом в главной роли.Новеченто - 1900-й - это не только цифра, обозначающая год. Так зовут гениального пианиста-самоучку, родившегося на борту океанского лайнера. У него нет ни документов, ни гражданства, ни родственников, только имя, данное кочегаром, нашедшим ребенка. 1900-й никогда не покидал",
                "Роман, Проза",
                R.drawable.oneninezerozero,
                210
        ));
        database.bookDAO().addBook(new Book(
                "Три товарища",
                "Эрих Мария Ремарк",
                "Действие происходит в Германии приблизительно в 1928-м году. Три товарища - Роберт Локамп (Робби), Отто Кестер и Готтфрид Ленц содержат небольшую авторемонтную мастерскую. Главный герой, автомеханик Робби познакомился с очаровательной девушкой Патрицией Хольман (Пат). Робби и Пат - люди разных судеб и из разных слоёв общества - полюбили друг друга. В романе показывается развитие их любви на фоне кризиса тех лет.",
                "Любовный роман, Реализм",
                R.drawable.remark,
                220
        ));
        database.bookDAO().addBook(new Book(
                "Маленький принц",
                "Антуан де Сент-Экзюпери",
                "Это сказка о ребенке, но сказка принявшая форму философской притчи. Она имеет адресат, посвящена другу Леону Верту. Фантастичен сюжет этой сказки,встреча потерпевшим аварию летчиком с пришельцем иных миров - забавным,крошечным человечком. Маленький принц свободно передвигается с одной планеты на другую, люди, животные имеют голоса, слышат и понимают друг друга. Мир предстает единым. Маленький принц просит нарисовать летчика барашка, и в этом рисунке он разглядел то, что не видят взрослые, противопоставление детей и взрослых, эта мысль проходит через всю сказку, и вопреки привычному - это дети учат взрослых доброте и верности и дети обладают высшей мудростью.",
                "Повесть, Притча",
                R.drawable.littleprince,
                2300
        ));
        database.bookDAO().addBook(new Book(
                "Код 51. Новая эра",
                "Грегори Кравински",
                "США. Октябрь 1971 года. К молодому и популярному телеведущему из Техаса по имени Алекс Свенсон попадают секретные документы, украденные из «Зоны 51». В документах содержится информация, которая способна потрясти сознание каждого человека на Земле. Это событие делит жизнь Алекса на «до» и «после». Теперь вместо того, чтобы отправиться в свадебное путешествие на Гавайи, Алекс вынужден скрываться от ЦРУ. Погони, перестрелки, предательство друзей и сфабрикованные обвинения загоняют Алекса в хижину, спрятанную в дебрях мексиканских лесов. Однако Алекс не собирается сдаваться. Он решает рассказать всю правду на диктофон и отправить записи в СМИ. Как только Алекс нажимает кнопку «REC», перед нами начинает раскрываться грандиозный по своим масштабам всемирный заговор… Комментарий Редакции: Во всем дойти до самой сути бывает непросто. Особенно трудно решать загадки, загаданные самой вселенной. Герои романа самоотверженно борются за установление истины и ее сохранение. Но готовы ли они положить на ее алтарь собственные жизни?",
                "Фантастика",
                R.drawable.code,
                500
        ));
        database.bookDAO().addBook(new Book(
                "Древний",
                "Сергей Тармашев",
                "Война - третья книга фантастической саги Древний Сергея Тармашева, продолжение романов Катастрофа и Корпорация. Главный герой эпоса - офицер-спецназовец Тринадцатый вновь вступает в борьбу за будущее Человечества. Остатки Корпорации окончательно разгромлены. Но долгожданный мир так и не пришел в Солнечную систему. Содружество Людей оказывается втянуто в Войну Пришедших После, глупое и кровавое соперничество за право признания Величайшими цивилизациями...",
                "Боевик",
                R.drawable.eternity,
                300
        ));
        database.bookDAO().addBook(new Book(
                "Джек Лондон",
                "Ирвинг Стоун",
                "<Отсутствует>",
                "Биография",
                R.drawable.london,
                900
        ));
        database.bookDAO().addBook(new Book(
                "Катрина: Число начала",
                "Алексей Кондратенко",
                "Редакционный фотограф оказывается втянут в борьбу за обладание пропавшим научным открытием убитого физика. Чтобы выжить, он должен помочь обворожительной сербской наемнице остановить опасную международную охранную сеть. Но может ли он доверить свою жизнь и судьбу человеческой цивилизации Катрине, чья ужасающая тайна многие века скрывалась под личиной мифов? Возвращение первоклассного вампирского триллера как жанра. Первый роман в серии",
                "Ужасы",
                R.drawable.catrina,
                600
        ));
        database.bookDAO().addBook(new Book(
                "Зеленая миля",
                "Стивен Кинг",
                "Стивен Кинг приглашает читателей в жуткий мир тюремного блока смертников, откуда уходят, чтобы не вернуться, приоткрывает дверь последнего пристанища тех, кто преступил не только человеческий, но и Божий закон. По эту сторону электрического стула нет более смертоносного местечка! Ничто из того, что вы читали раньше, не сравнится с самым дерзким из ужасных опытов Стивена Кинга — с историей, что начинается на Дороге Смерти и уходит в глубины самых чудовищных тайн человеческой души… Глубокий старик Пол Эджкомб, бывший тюремный надзиратель над смертниками, вспоминает необыкновенные события особенной в его жизни осени 1932 года, когда в нее вошел странный чернокожий гигант Джон Коффи. В 1999 году по роману был снят одноимённый фильм.",
                "Фантастика, Ужасы",
                R.drawable.greenmile,
                300
        ));
        database.bookDAO().addBook(new Book(
                "Клуб неисправимых оптимистов",
                "Жан Мишель Генассия",
                "Жан-Мишель Генассия, новое имя в европейской прозе, автор романа КЛУБ НЕИСПРАВИМЫХ ОПТИМИСТОВ. Французские критики назвали его книгу великой, а французские лицеисты вручили автору Гонкуровскую премию. Герою романа двенадцать лет. Это Париж начала шестидесятых. И это пресловутый переходный возраст, когда все: школа, общение с родителями и вообще жизнь - дается трудно. Мишель Марини ничем не отличается от сверстников, кроме увлечения фотографией и самозабвенной любви к чтению. А еще у него есть тайное убежище - это задняя комнатка парижского бистро. Там странные люди, бежавшие из стран, отделенных от свободного мира железным занавесом, спорят, тоскуют, играют в шахматы в ожидании, когда решится их судьба. Удивительно, но именно здесь, в этой комнатке, прозванной Клубом неисправимых оптимистов, скрещиваются силовые линии эпохи.",
                "Проза",
                R.drawable.club,
                20
        ));
        database.bookDAO().addBook(new Book(
                "Рассечение Стоуна",
                "Абрахам Вергезе",
                "Большой эпический роман, написанный одним из самых авторитетных врачей мира. Рассечение Стоуна - история любви длиною в жизнь, предательства и искупления, человеческой слабости и силы духа, изгнания и долгого возвращения к корням. В миссионерской больнице Адис-Абебы при трагических, истинно шекспировских, обстоятельствах рождаются два мальчика, два близнеца, сросшихся затылками, Марион и Шива. Рожденные прекрасной индийской монахиней от хирурга-англичанина, мальчики осиротели в первые часы жизни. Искусство и мужество врачей, разделивших их сразу после рождения, определило их жизнь и судьбу. Мэрион и Шива свяжут свою жизнь с медициной, но каждый пойдет своей дорогой. Их ждет удивительная, трагическая и полная невероятных событий судьба. Абсолютно счастливое детство и драматическая юность, поиски себя и своих корней, предательство и страстное желание искупить вину, любовь, похожая на наваждение, и ревность, изъедающая душу. И все это под сенью медицины.",
                "Проза",
                R.drawable.stone,
                50
        ));
        database.bookDAO().addBook(new Book(
                "Шерлок Холмс",
                "Артур Конан Дойл",
                "Произведения, посвящённые приключениям Шерлока Холмса, знаменитого лондонского частного детектива, считаются классикой детективного жанра. В цикл произведений о знаменитом сыщике Шерлоке Холмсе входят четыре повести - Этюд в багровых тонах, Знак четырех, Собака Баскервилей, Долина ужаса, а также пять сборников рассказов - «Приключения Шерлока Холмса», «Воспоминания Шерлока Холмса» («Записки о Шерлоке Холмсе»), «Возвращение Шерлока Холмса», «Его прощальный поклон», «Архив Шерлока Холмса». В общей сложности Шерлок Холмс появляется в 56 рассказах и 4 повестях Артура Конан Дойла. В большинстве случаев повествование ведётся от имени лучшего друга и спутника Холмса — доктора Ватсона.",
                "Детективы, Проза",
                R.drawable.sherlock,
                100
        ));
        database.bookDAO().addBook(new Book(
                "Вторая жизнь Уве",
                "Фредрик Бакман",
                "Вниманию всех, кто знает толк в скандинавской литературе и в особенности в шведской: наконец-то на русском языке вышел долгожданный роман Фредрика Бакмана Вторая жизнь Уве! На мировой рынок шведы экспортируют преимущественно три категории текстов: детективы (от корифеев - Май Шёваль с Пером Валё и Хеннига Манкелля - до Лизы Марклунд и Ларса Кеплера); психологическую прозу (от Стриндберга и Лагерквиста до Майгулль Аксельссон и Карин Альвтеген); и, наконец, прозу юмористическую.",
                "Проза",
                R.drawable.secondlife,
                300
        ));
        database.bookDAO().addBook(new Book(
                "Мастер и Маргарита",
                "Михаил Булгаков",
                "Один из самых загадочных и удивительных романов XX века. «Мастер и Маргарита» – визитная карточка Михаила Булгакова. Более десяти лет он работал над книгой, которая стала его романом-судьбой, романом-завещанием. В «Мастере и Маргарите» есть всё: весёлое озорство и щемящая печаль, романтическая любовь и колдовское наваждение, магическая тайна и безрассудная игра с нечистой силой.",
                "Детская литература",
                R.drawable.catrina,
                400
        ));

        books = database.bookDAO().getAllBooks();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}