# Android-Kim-Dabin
구현 내용은 아래를 참고해주세요!
<details> 
 <summary>week1</summary>
 <!-- summary 아래 한칸 공백 두어야함 --> 
 
| SignInActivity | SignUpActivityt  |  
|:----------|:----------:|
| <img src="https://user-images.githubusercontent.com/84564695/136655876-68f490b0-fd21-438c-aef9-3e363e9ea12e.gif" width="200" height="380"/> | <img src="https://user-images.githubusercontent.com/84564695/136656583-3aa640b4-7281-436b-bf82-274b4e2ed58e.gif" width="200" height="380"/> |
*hint속성..짤 화질이 안좋아서 잘 안보임니다 |회원가입 Text..짤 화질이 안좋아서 잘 안보임다
## ✅Level1+Level2
### ✔***SignInActivity***
 - #### 데이터 입력 확인
```kotlin
   //모든 데이터가 입력되었는지 확인
        fun isEtIdEmpty(): Boolean {
            return binding.etIdIn.text.isNullOrEmpty()
        }

        fun isEtPassword(): Boolean {
            return binding.etPassIn.text.isNullOrEmpty()
        }

        fun isAllEditTextEmpty(): Boolean {
            return isEtIdEmpty() || isEtPassword()
        }
```

   - ####  isAllEditTextEmpty에 따른 분기 처리

```kotlin
 //로그인 버튼 눌렀을때 데이터 입력에 따른 분기 이벤트
        binding.bvLogin.setOnClickListener {
            if (isAllEditTextEmpty() == true) {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "${binding.etIdIn.text}님 환영합니다", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
```
- #### 회원가입 버튼 클릭 시
```kotlin
  //회원가입 버튼시 이벤트->데이터 리턴받아와야함!
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            getResultText.launch(intent)
        }

        //회원가입 데이터 받아오기
        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == 1) {
                val id = result.data?.getStringExtra("id")
                val pass = result.data?.getStringExtra("pass")
                binding.etIdIn.setText(id)
                binding.etPassIn.setText(pass)
            } else {
                binding.etIdIn.text.clear()
                binding.etPassIn.text.clear()
            }
        }
```
### ✔***SignUpActivity***

  - #### 데이터 입력 확인
```kotlin
   fun isEtNameEmpty(): Boolean {
            return binding.etName.text.isNullOrEmpty()
        }

        fun isEtIdEmpty(): Boolean {
            return binding.etId.text.isNullOrEmpty()
        }

        fun isEtPassword(): Boolean {
            return binding.etPass.text.isNullOrEmpty()
        }

        fun isAllEditTextEmpty(): Boolean {
            return isEtNameEmpty() || isEtIdEmpty() || isEtPassword()
        }
```
  - #### isAllEditTextEmpty에 따른 분기 처리
```kotlin
     //회원가입 완료 버튼 분기 이벤트
        binding.bvLogin.setOnClickListener {
            if (isAllEditTextEmpty() == true) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("id", binding.etId.text.toString())
                intent.putExtra("pass", binding.etPass.text.toString())

                setResult(1, intent)
                finish() //화면이동시 intent아닌 finish로 스택에서 제거
            }
        }
```

### ✔***HomeActivity***
- #### 깃허브 페이지로 이동
```kotlin
  //깃허브 페이지로 이동
        binding.ivGithubIcon.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/dabinKim-0318"))
            startActivity(intent)
        }
```
 - 명시적 Intent:
앱 안에서 Activity나 서비스 같은 구성 요소를 시작할 때 사용하기 때문에 시작하고자 하는 액티비티 또는 서비스의 클래스 이름을 알고 있어야함
val intent2 = Intent(this@Activity_1, Activity_2::class.java)
ex)새로운 액티비티를 시작하거나 백그라운드에서 파일을 다운로드하기 위해 서비스를 시작하는 것
위에서 Activity1->Activity2 간 Intent를 전달하는 예제 역시 명시적 Intent유형이었음

 - 암시적 Intent:
특정 구성 요소의 이름을 대지 않지만, 그 대신 수행할 일반적인 작업을 선언하여 다른 앱의 구성 요소가 이를 처리할 수 있도록 할 때 사용
val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"))
ex)사용자에게 지도에 있는 한 위치를 표시하고자 하는 경우, 암시적 인텐트를 사용하여 해당 기능을 갖춘 다른 앱이 지정된 위치를 지도에 표시하도록 요청
### ✔***그외***
- 모든 데이터가 입력되었을때만 로그인/회원가입 버튼 색깔 바뀌게하기
```kotlin
  //모든 데이터가 입력되었을때 로그인 버튼 색깔 바뀌게하기->addTextChangedListener 사용
        binding.etPassIn.addTextChangedListener(object : TextWatcher {
            @SuppressLint("ResourceAsColor")
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (isEtIdEmpty() != true && isEtPassword() != true && p0.toString() != "") {
                    binding.bvLogin.setBackgroundResource(R.drawable.login_background2)
                }
            }
        })
 ```
## ✅Step3
### ✔***DataBinding***
```kotlin
  val binding: ActivityDataBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding)
        binding.user = User(
            "SOPTHub", "이름", "나이", "MBTI", "짱구",
            "6", "CUTE", "짱구는 흰둥이를 좋아해"
        )

        binding.face = Databinding_image("https://mblogthumb-phinf.pstatic.net/MjAxODEyMDVfMjY5/MDAxNTQ0MDA3NDgyNjgw.v21vfp4yFzGtYlNrFPeo7Cxkd6ZVa3ZNKeRwZe5l3e0g.y2pAI3tJYWq04q_FwbVgTOoTVo9bKcwISdhj9EAxNYgg.GIF.nang723/IMG_0834.GIF?type=w800")


        binding.ivGithubIcon.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/dabinKim-0318"))
            startActivity(intent)
        }
```
```kotlin
data class User(
    val tv_title: String,
    val tv_name: String,
    val tv_age: String,
    val tv_mbti: String,
    val tv_name2: String,
    val tv_age2: String,
    val tv_mbti2: String,
    val tv_like: String,
)
```
```kotlin
class Databinding_image(val profile: String) {
    object MyBind {
        @JvmStatic
        @BindingAdapter("setImage")
        fun setImageUrl(view: ImageView, profile: String) {

            Glide.with(view.context)
                .load(profile)
                .into(view)
        }
    }
}
```
### ✔setOnClickListener
 setOnClickListener를 람다식으로 간결하게 표현할 수 있는 이유: SAM(Single Abstract Method) 변환
 구현하는 인터페이스(View.OnClickListener)에 구현하는 메소드가 하나(onClick)뿐일때는 이를 람다식으로 변경할 수 있습니다. 

## ✅배운 것
- fade_in fade_out 애니메이션 구현하는 법 
- 이미지에 gif 넣는 법
- isNullOrEmpty()가 string타입이 아닌 editable타입에서도 사용할 수 있다는 것
- startActivityForResult가 deprecated된 거 흐린 눈 하고 있었는데..ㅎㅎ registerForActivityResult로 데이터 리턴 받는 법을 배움
- addTextChangedListener로 editText 입력값 실시간으로 이벤트 처리하는 법
- Data Binding사용!!
</details> 

<details> 
 <summary>week2</summary>
 <!-- summary 아래 한칸 공백 두어야함 --> 
 
| RepositoryRecyclerView | FollowerRecyclerView  |  
|:----------|:----------:|
| <img src="https://user-images.githubusercontent.com/84564695/138446951-106bd619-6cc6-4ed2-b233-66684c8a8b50.gif" width="200" height="380"/> | <img src="https://user-images.githubusercontent.com/84564695/138447609-17856a66-0617-4068-831a-d49dcd337c10.gif" width="200" height="380"/> |




### ✔***FollowerRecyclerView***
 - #### FollowerRecyclerViewAdapter
```kotlin
  class FollowerRecyclerViewAdapter(val activity: Context) : RecyclerView.Adapter<FollowerRecyclerViewAdapter.FollowerViewHolder>() {
    val followerList = mutableListOf<Introduce_SOPT>()

    inner class FollowerViewHolder(private val view: ItemFollowerListBinding) : RecyclerView.ViewHolder(view.root) {
        fun onBind(data: Introduce_SOPT) {
            view.tvName.text = data.name
            view.tvStory.text = data.story

            itemView.setOnClickListener { view: View ->
                val intent = Intent(view.context, DetailActivity::class.java)
               intent.putExtra("name", data.name)
                view.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerRecyclerViewAdapter.FollowerViewHolder {
        val view = ItemFollowerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(view)
    }

    override fun getItemCount() = followerList.size //한줄로 리턴되는 함수 가독성~

    override fun onBindViewHolder(holder: FollowerRecyclerViewAdapter.FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])


    }
```
### ✔***RepositoryRecyclerView***
   - ####  RepositoryRecyclerViewAdapter

```kotlin
 class RepositoryRecyclerViewAdapter() : RecyclerView.Adapter<RepositoryRecyclerViewAdapter.RepositoryViewHolder>(), ItemTouchHelperListener {
    val followerList = mutableListOf<Introduce_SOPT>()

    override fun onItemMove(from_position: Int, to_position: Int): Boolean {
        val item = followerList[from_position]
        followerList.removeAt(from_position)
        followerList.add(to_position, item)
        notifyDataSetChanged()
        return true
    }

    override fun onItemSwipe(position: Int) {
        followerList.removeAt(position)
        notifyItemRemoved(position)
    }

    class RepositoryViewHolder(private val view: ItemRepositoryListBinding) : RecyclerView.ViewHolder(view.root) {
        fun onBind(data: Introduce_SOPT) {
            view.tvName.text = data.name
            view.tvStory.text = data.story

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = ItemRepositoryListBinding.inflate(LayoutInflater.from(parent.context))
        return RepositoryViewHolder(view)
    }

    override fun getItemCount() = followerList.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.onBind(followerList[position])
    }

}

```

## ✅Level2
### ✔***DetailActivity로 데이터 전달***
   - #### FollowerRecyclerView
```kotlin
     inner class FollowerViewHolder(private val view: ItemFollowerListBinding) : RecyclerView.ViewHolder(view.root) {
        fun onBind(data: Introduce_SOPT) {
            view.tvName.text = data.name
            view.tvStory.text = data.story

            itemView.setOnClickListener { view: View ->
                val intent = Intent(view.context, DetailActivity::class.java)
               intent.putExtra("name", data.name)
                view.context.startActivity(intent)
            }
        }
    }
```
 - #### DetailActivity
```kotlin
       var name = intent.getStringExtra("name").toString()
        binding.tvMyname.text = name
```
FollowerViewHolder에서 setOnClickListener를 구현해 데이터를 전달하긴했는데, 이미지도 position에 따라 같이 전달되는 방법 없을까.. 더 공부가 필요한 부분쓰,,

### ✔***ItemDecoration***
   - #### VerticalItemDecorator 만들기
   ```kotlin
   import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class VerticalItemDecorator(
    context: Context,
    resId: Int,
    val paddingLeft: Int,
    val paddingRight: Int,
    val divHeight: Int
) : RecyclerView.ItemDecoration() {
    private var mDivider: Drawable? = null

    init {
        mDivider = ContextCompat.getDrawable(context, resId)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft + paddingLeft
        val right = parent.width - parent.paddingRight - paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + (mDivider?.intrinsicHeight ?: 0)
            mDivider?.let {
                it.setBounds(left, top, right, bottom)
                it.draw(c)
            }
        }
    }

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        super.getItemOffsets(outRect, itemPosition, parent)
        outRect.top = divHeight
    }
}

   ```

- #### RepositoryRecyclerView에 VerticalItemDecorator연결
```kotlin
binding.container.addItemDecoration(VerticalItemDecorator(activity, R.drawable.repository_line_divider, 60, 60, 30))
```
   
 ### ✔***RecyclerView이동,삭제 구현***
- #### ItemTouchHelperListener
 ```kotlin
    interface ItemTouchHelperListener {
    fun onItemMove(from_position: Int, to_position: Int):Boolean
    fun onItemSwipe(position: Int)
}
 ```
- #### ItemTouchHelperCallback
```kotlin
 class ItemTouchHelperCallback(val listener: ItemTouchHelperListener): ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val drag_flags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipe_falgs = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(drag_flags, swipe_falgs)
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return listener.onItemMove(viewHolder.adapterPosition, target.adapterPosition) as Boolean
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onItemSwipe(viewHolder.adapterPosition)
    }
}
```
 - #### RepositoryRecyclerView
```kotlin
     class RepositoryRecyclerViewAdapter() : RecyclerView.Adapter<RepositoryRecyclerViewAdapter.RepositoryViewHolder>(), ItemTouchHelperListener {
    ...
    override fun onItemMove(from_position: Int, to_position: Int): Boolean {
        val item = followerList[from_position]
        followerList.removeAt(from_position)
        followerList.add(to_position, item)
        notifyDataSetChanged()
        return true
    }

    override fun onItemSwipe(position: Int) {
        followerList.removeAt(position)
        notifyItemRemoved(position)
    }
    ...
 ```
  - #### repositoryAdapter에 연결
 ```kotlin
    ItemTouchHelper(ItemTouchHelperCallback(repositoryAdapter)).attachToRecyclerView(binding.container)
```
   
   
## ✅Level3
### ✔ fragment 의 보일러 플레이트 코드
  - 보일러 플레이트 코드:최소한의 변경으로 여러곳에서 재사용되며, 반복적으로 비슷한 형태를 띄는 코드
  - 해결법: 라이브러리, 어노테이션 프로세서, 플러그인 등을 사용하면 된다고 하는데 아직 스스로 구현하기 무리인 것 같다 셤 끝나면 도전!
### ✔ notifyDataSetChanged
리사이클러뷰는 어댑터의 메소드를 통해 아이템 변경을 감지하고 갱신할 수 있다.
그러나 정확히 어떤 아이템이 변경되었는지는 알 수 없기 때문에 변경된 아이템의 position을 알려줘야 한다.

   - notifyDataSetChanged
:아이템 변경(데이터가 업데이트 되었지만 위치는 변하지 않았을 때), 구조적 변경(아이템간에 삽입, 삭제, 이동이 일어났을 때)에 사용한다.

  - notifyItemChanged / notifyItemChanged(int position, Object payload)
:position 위치의 아이템이 변경되었다고 파라미터를 통해 알려줄 수 있다.

  - notifyItemInserted / notifyItemInserted(int position)
:position 위치에 아이템이 추가되었다는 뜻이다.

  - notifyItemMoved / notifyItemMoved(int fromPostion, int toPosition)
:인덱스 fromPosition 아이템이 toPosition으로 이동하였다.

  - notifyItemRangeChanged / notifyItemRangeChanged(int positionStart, int itemCount, Object payload)
:positionStart부터 itemCount개까지 범위에서 변경이 일어났다.


- 문제점:notifyDataSetChanged는 리스트의 크기와 아이템이 둘 다 변경되는 경우에 사용하는 거라 리스트의 크기는 동일한데 아이템만 바뀌는 경우라든지 아이템의 순서만 살짝 바뀌는 경우 등등에는 굳이 notifyDataSetChanged를 사용할 필요가 없다. 
   notifyDataSetChanged는 어느 상황에서나 사용 가능하지만 문제가 된다면, 성능이 비효율적
   
   
## ✅배운점
- 매번 Activity에서 RecyclerView를 사용했는데 Fragment에서 RecyclerView띄우는 걸 배웠다
- Fragment는 context처리하는게 귀찮다는 점..ㅎㅎ
- RecyclerView에서 아이템 스와이프하고 이동시키는 법
- layoutManager를 항상 코드에서 조작했는데 xml에서도 가능한 걸 알았다

 </details>

 
 <details> 
 <summary>week3</summary>
 <!-- summary 아래 한칸 공백 두어야함 --> 
 
| Level1,2 | Level3  |  
|:----------|:----------:|
| <img src="https://user-images.githubusercontent.com/84564695/139577909-72861c9a-8943-4eca-a893-229009a22d39.gif" width="200" height="380"/> | <img src="https://user-images.githubusercontent.com/84564695/139577974-02ec31e6-0542-4906-a27d-30d02626cfad.gif" width="200" height="380"/> |
  
## ✅Level1
### ✔ ***EditText에 selector 활용***
- #### SignIn/Up Activity
```xml
    ~selector 만들기~
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_focused="true"  android:drawable="@drawable/sign_input_pinkline"/>
    <item android:drawable="@drawable/sign_input_grayline"/>
</selector>
```

```xml
      ~직접 도형 그리기~
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:bottom="1dp"
        android:left="1dp"
        android:right="1dp"
        android:top="1dp">

        <shape android:shape="rectangle">
            <stroke
                android:width="1dp"
                android:color="@color/hintInerGray" />
            <solid android:color="@color/Gray6" />
            <corners
                android:bottomLeftRadius="5dp"
                android:bottomRightRadius="5dp"
                android:topLeftRadius="5dp"
                android:topRightRadius="5dp" />
        </shape>

    </item>
</layer-list>
```
  
### ✔ ***버튼에 selector 활용***
- #### Home
```xml
  ~버튼 택스트 isSelected에 따른 색 변경하는 selector파일~
<selector xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:state_selected="true" android:color="@color/white" />
    <item android:state_selected="false" android:color="@color/Gray1" />

</selector>
 ```
 ```kotlin
  ~isSelected에 따른 분기 처리~
        binding.bvFollower.setOnClickListener {
            if (binding.bvRepository.isSelected == true) {
                binding.bvRepository.isSelected = false
                binding.bvFollower.isSelected = true
                childFragmentManager
                    .beginTransaction()
                    .replace(R.id.home_container, followerRecyclerViewFragment)
                    .commit()
            } else {
                childFragmentManager
                    .beginTransaction()
                    .replace(R.id.home_container, followerRecyclerViewFragment)
                    .commit()
            }
        }

        binding.bvRepository.setOnClickListener {
            if (binding.bvFollower.isSelected == true) {
                binding.bvFollower.isSelected = false
                binding.bvRepository.isSelected = true
                childFragmentManager
                    .beginTransaction()
                    .replace(R.id.home_container, repositoryRecyclerViewFragment)
                    .commit()
            } else {
                childFragmentManager
                    .beginTransaction()
                    .replace(R.id.home_container, repositoryRecyclerViewFragment)
                    .commit()
            }
        }
    }
  ```
## ✅Level2
### ✔ ***ViewPager2 중첩 스크롤 문제 해결***
- #### HomeFragment : 구글에서 제공하는 [NestedScrollableHost](https://github.com/android/views-widgets-samples/blob/master/ViewPager2/app/src/main/java/androidx/viewpager2/integration/testapp/NestedScrollableHost.kt) 를 사용
  ```xml
      <com.example.sopt_assignment_dabin.ViewPager_Fragment.NestedScrollableHost
        android:id="@+id/nestedScrollableHost"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
      app:layout_constraintTop_toBottomOf="@+id/tl_home">

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/vp_home"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:tabIndicatorGravity="top" />
    </com.example.sopt_assignment_dabin.ViewPager_Fragment.NestedScrollableHost>
  ```

  
### ✔ ***아이템에 이미지 url을 활용해 Glide로 서로 다른 이미지를 띄우기***
- #### FollowerListData : image 변수 추가
```kotlin
  data class FollowerListData(
    val name:String,
    val story:String,
    val image:String)
```
  
- #### FollowerRecyclerViewAdapter: FollowerRecyclerViewAdapter에서 이미지 띄우는 Glide사용
  
```kotlin
  fun onBind(data: FollowerListData) {
            view.tvName.text = data.name
            view.tvStory.text = data.story
            Glide.with(itemView.context).load(data.image).circleCrop().into(view.ivFollowerImage)
```

## ✅Level3
### ✔ ***갤러리에서 받아온 이미지(uri)를 Glide로 화면에 띄우기***
- #### CameraBackgroundViewpager: isGranted인 경우가 true인지 false인지 분기 처리 후 권한허용이라면 startProcess() 실행 후 CallBack으로 이미지 띄우기
```kotlin
      private fun checkPermission() {
        val cameraPermission = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (cameraPermission == PackageManager.PERMISSION_GRANTED) {
            //프로그램 진행
            startProcess()
        } else {
            //권한요청
            requestPermission()
        }
    }
  
      private fun requestPermission() {
        permissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }
```
  
```kotlin
   private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        when (isGranted) {
            true -> startProcess()
            false -> Toast.makeText(getActivity(), "갤러리 권한을 허용해주세요.", Toast.LENGTH_SHORT).show()
        }
    }
  
    private fun startProcess() {
        val intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        getResultText.launch(intent)
    }

    var getResultText = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val uri = intent?.data
            Glide.with(this).load(uri).into(binding.ivCamera)
        }
        //else if (result.resultCode == Activity.RESULT_CANCELED) {} =>Activity.RESULT_CANCELED일때 처리코드가 필요하다면
    }
```

## ✅배운 것
- 디자인 적용 처음해보는데 너무 신기하고..조금 귀찮고...재밌었다
- TabLayout 커스터마이징 하는법
- ViewPager안에 ViewPager있으면 중첩이 안된다는 것
- Fragment안에 Fragment넣기는 Activity에 종속될때랑 코드가 달라짐
- 권한처리에 대해 배웠는데 어려워서 더 배워야 할 듯  
</details>

  <details> 
 <summary>week4</summary>
 <!-- summary 아래 한칸 공백 두어야함 --> 
 
|  | 작고 귀여운.. 팔로워들,,^^  |  
|:----------|:----------:|
| <img src="https://user-images.githubusercontent.com/84564695/141331545-2e27d292-5ab0-4988-9ebd-ff7fd224f4f8.gif" width="200" height="380"/> | <img src="https://user-images.githubusercontent.com/84564695/141331246-4a342d22-d075-4bde-826a-e467eeafea0c.gif" width="200" height="380"/> |
  
## ✅Level1
### ✔ ***포스트맨***
![1](https://user-images.githubusercontent.com/84564695/141328684-ec8323f3-f94d-4c05-9641-b2770c9c5a52.PNG)
   ***
![2](https://user-images.githubusercontent.com/84564695/141328691-ed9c5b1d-37f7-4f51-8b77-313153e38a2c.PNG)

## ✅Level1&2-1&2-2
### ✔ ***Github API연동***
- #### GithubService
```kotlin
   interface GithubService {
    @GET("/users/{username}/repos")
    fun githubRepoGet(
        @Path("username") username: String
    ): Call<List<RepositoryResponseData>>


    @GET("/users/{username}/followers")
    fun githubFollowerGet(
        @Path("username") username: String
    ): Call<List<FollowerResponseData>>


    @GET("/users/{username}")
    fun githubBioGet(
        @Path("username") username: String
    ): Call<FollowerResponseDataBio> }
```
- #### Data
```kotlin
  data class FollowerResponseData(
    @SerializedName("login")
    val followerId: String,
    @SerializedName("avatar_url")
    val followerProfile: String
)

data class FollowerResponseDataBio(
val bio: String?
)

data class FollowerData(
    val followerId: String,
    val followerProfile: String,
    val followerBio: String
)
```
### ✔ ***중복되는 헤더 없애기***
- #### GithubServiceCreator
```kotlin
   object GithubServiceCreator {
    private val BASE_URL = "https://api.github.com"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideOkHttpClient(AppInterceptorGit()))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val githubService: GithubService = retrofit.create(GithubService::class.java)
}

private fun provideOkHttpClient(
    interceptor: AppInterceptorGit
): OkHttpClient = OkHttpClient.Builder()
    .run {
        addInterceptor(interceptor)
        build()
    }

class AppInterceptorGit : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain)
            : okhttp3.Response = with(chain) {
        val newRequest = request().newBuilder()
            .addHeader("accept", "application/vnd.github.v3+json")
            .build()
        proceed(newRequest)
    }
}
```

## ✅Level2-3
### ✔ ***중복되는 request/response데이터 없애기***
- #### SignResponseWrapperData: SignResponseWrapperData 를 만들어서 request/respond 데이터를 감싸줌
```kotlin
    data class SignResponseWrapperData<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T? = null
)
```
```kotlin
   interface SignupService {
    @POST("user/signup")
    fun signupLogin(
        @Body body: SignupRequestData
    ): Call<SignResponseWrapperData<SiginupResponseData>>
}

interface SigninService {
    @POST("user/login")
    fun signinLogin(
        @Body body: SigninRequestData
    ): Call<SignResponseWrapperData<SigninResponseData>>
}
```

## ✅배운 것
- 포스트맨사용법 
- retrofit이용해서 서버 통신하는 법
- OkHttp 사용하는 법
- Wrapper클래스로 공통 부분 묶을 수 있다는 것
- 팔로워 bio 넣어서 새로운 리스트 만들면서 왜 안드로이드 아키텍쳐를 배워야하는지 뼈저리게 느꼈다......
  서버 통신부분이랑 데이터 조작하는 부분, UI그리는 부분 등이 분리가 안되어있으니까 머리가 핑 돌았다. 빨리 아키텍쳐 패턴 공부해야겟다....................
- 팔로워 bio 불러오는거 구현하긴 했는데 우당탕탕 돌아가는 느낌이다. 스파게티 코드란 이런걸까? ㅋ..ㅋ
</details>
 
   <details> 
 <summary>week7</summary>
 <!-- summary 아래 한칸 공백 두어야함 --> 
 


https://user-images.githubusercontent.com/84564695/146494606-e2c53548-c03b-4260-a55e-5eb74d075acc.mp4


https://user-images.githubusercontent.com/84564695/146494533-28e2930c-f6d2-4478-ab56-fb02ce97ac56.mp4



## ✅Level1-1,1-2
### ✔***온보딩 만들기***
 - #### layout
```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_onboarding"
    app:startDestination="@id/onBoardingFragment1">

    <fragment
        android:id="@+id/onBoardingFragment1"
        android:name="com.example.sopt_assignment_dabin.ui.OnBoardingFragment1"
        android:label="첫번째 화면"
        tools:layout="@layout/fragment_on_boarding1">
        <action
            android:id="@+id/action_onBoardingFragment1_to_onBoardingFragment2"
            app:destination="@id/onBoardingFragment2" />
    </fragment>
    <fragment
        android:id="@+id/onBoardingFragment2"
        android:name="com.example.sopt_assignment_dabin.ui.OnBoardingFragment2"
        android:label="두번째 화면"
        tools:layout="@layout/fragment_on_boarding2">
        <action
            android:id="@+id/action_onBoardingFragment2_to_onBoardingFragment3"
            app:destination="@id/onBoardingFragment3" />
    </fragment>
    <fragment
        android:id="@+id/onBoardingFragment3"
        android:name="com.example.sopt_assignment_dabin.ui.OnBoardingFragment3"
        android:label="세번째 화면"
        tools:layout="@layout/fragment_on_boarding3">
        <action
            android:id="@+id/action_onBoardingFragment3_to_onBoardingFragment1"
            app:destination="@+id/onBoardingFragment1"
            app:popUpTo="@+id/onBoardingFragment1"
            app:popUpToInclusive="true" />
        <action
            android:id="@+id/action_onBoardingFragment3_to_signInActivity"
            app:destination="@id/signInActivity" />
    </fragment>
    <activity
        android:id="@+id/signInActivity"
        android:name="com.example.sopt_assignment_dabin.ui.SignInActivity"
        android:label="activity_sign_in"
        tools:layout="@layout/activity_sign_in" />
</navigation>
```
### ✔***SharedPreferences 활용해서 자동로그인/자동로그인 해제***
```kotlin
  object AutoLogin {

    private const val STORAGE_KEY = "com.example.sopt_assignment_dabin.app"
    val ONBOARDING = "ONBOARDING"
    val AUTO_LOGIN = "AUTO_LOGIN"
    val USER_ID = "USER_ID"
    val USER_PASS = "USER_PASS"

    fun setOnBoarding(context: Context, init: Boolean) {
        getSharedpf(context).edit()
            .putBoolean(ONBOARDING, init)
            .apply()
    }

    fun getOnBoarding(context: Context): Boolean {
        return getSharedpf(context).getBoolean(ONBOARDING, true)
    }

    fun getAutoLogin(context: Context): Boolean {
        return getSharedpf(context).getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(context: Context, checked: Boolean, id: String = "", pass: String = "") {
        getSharedpf(context).edit()
            .putBoolean(AUTO_LOGIN, checked)
            .putString(USER_ID, id)
            .putString(USER_PASS, pass)
            .apply()
    }

    fun removeAutoLogin(context: Context) {
        getSharedpf(context).edit()
            .remove(USER_ID)
            .remove(USER_PASS)
            .apply()
    }

    fun getSharedpf(context: Context): SharedPreferences {
        return context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
    }
}
```
- 실제 파일엔 Room으로 바꿔서 해당 SharedPreferences 사용 로직은 주석처리되어 있습니다!
  
### ✔**본인이 사용하는 Util 클래스 코드 및 패키징 방식 리드미에 정리**
  ![](https://images.velog.io/images/dabin/post/d04708a1-8d6b-476d-bbcd-e792c01d8811/image.png)
  - 과제에서 배운 내용 기반으로 리팩토링 했습니다
  - Util 클래스는 토스트 띄우는 확장함수랑 RoomDAO가져오는 확장함수 사용했어여
  
## ✅Level2-1,2-2
### ✔***NavigationComponent에서 BackStack관리+NavigationComponent와 ToolBar 연동***
```XML
      <androidx.appcompat.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/black"
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:popupTheme="@style/ThemeOverlay.AppCompat.Light" />
```
  - 툴바 만들기
```xml
      <fragment
        android:id="@+id/onBoardingFragment3"
        android:name="com.example.sopt_assignment_dabin.ui.OnBoardingFragment3"
        android:label="세번째 화면"
        tools:layout="@layout/fragment_on_boarding3">
        <action
            android:id="@+id/action_onBoardingFragment3_to_onBoardingFragment1"
            app:destination="@+id/onBoardingFragment1"
            app:popUpTo="@+id/onBoardingFragment1"
            app:popUpToInclusive="true" />
        <action
            android:id="@+id/action_onBoardingFragment3_to_signInActivity"
            app:destination="@id/signInActivity" />
    </fragment>
    <activity
        android:id="@+id/signInActivity"
        android:name="com.example.sopt_assignment_dabin.ui.SignInActivity"
        android:label="activity_sign_in"
        tools:layout="@layout/activity_sign_in" />
```
  - xml에서 `app:popUpTo`, `app:popUpToInclusive` 속성 추가하고 action추가하기
```kotlin
 override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_onBoardingFragment3_to_onBoardingFragment1)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoarding3Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btNext.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    requireActivity().getHelper().insert(RoomLogin(false, false, "", ""))
                } catch (e: Exception) {
                    e.toString()
                }
            }
            findNavController().navigate(R.id.action_onBoardingFragment3_to_signInActivity)
            requireActivity().finish()
        }
    }
```
  - Fragment3에서 NavigationComponent에서 BackStack관리+NavigationComponent와 ToolBar 연동하는 로직
## ✅Level3
### ✔***Room을 활용해서 자동로그인 로직 만들기***
```kotlin
  @Entity(tableName = "table_login")
class RoomLogin {
    @ColumnInfo
    var onBording = true

    @ColumnInfo
    var autoLogin = false

    @PrimaryKey
    @ColumnInfo
    var id = ""

    @ColumnInfo
    var password = ""

    constructor(onBording: Boolean = true, autoLogin: Boolean = false, id: String, password: String) {
        this.onBording = onBording
        this.autoLogin = autoLogin
        this.id = id
        this.password = password
    }
}
```
  - 테이블 만들기
```kotlin
@Dao
interface RoomDAO {

    //유저 정보 만들기
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(login: RoomLogin)

    //유저 정보 얻기
    @Query("SELECT * FROM table_login ")
    fun get(): RoomLogin

    //자동로그인 선택시 true로 바꾸고 정보 업데이트
    @Query("UPDATE table_login SET autoLogin =:autoLogin,id=:id,password=:password")
    fun updateInfo(autoLogin: Boolean, id: String, password: String)

    //설정에서 자동로그인 해제 시 false로 바꾸기
    @Query("UPDATE table_login SET autoLogin =:autoLogin")
    fun updateLogin(autoLogin: Boolean)

    //설정에서 자동로그인 해제 시 유저정보 삭제
    @Query("DELETE FROM table_login WHERE id = :id")
    fun delete(id: String)
}
```
  - RoomDAO 만들기  
  
```kotlin
@Database(entities = [RoomLogin::class], version = 1, exportSchema = false)
abstract class RoomHelper : RoomDatabase() {
    abstract fun roomInfoDao(): RoomDAO

    companion object {
        private var instance: RoomHelper? = null

        @Synchronized
        fun getInstance(context: Context): RoomHelper? {
            if (instance == null) {
                synchronized(RoomHelper::class) {
                    instance = Room.databaseBuilder(
                        context,
                        RoomHelper::class.java,
                        "login_database"
                    ).build()
                }
            }
            return instance
        }
    }
}
```
  - 데이터베이스 역할하는 RoomHelper 만들기  
```kotlin
      //자동 로그인클릭 이벤트 발생
    private fun autoClickEvent() {
        binding.ctAutoLogin.setOnClickListener {
            binding.ctAutoLogin.toggle()
            setAutoLogin(binding.ctAutoLogin.isChecked, binding.etIdIn.text.toString(), binding.etPassIn.text.toString())
        }
    }

    //자동로그인 선택시 유저 정보 저장
    private fun setAutoLogin(autoLogin: Boolean, id: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                getHelper().updateInfo(autoLogin, id, password)
            } catch (e: Exception) {
                e.toString()
            }
        }
    }

    //자동 로그인 체크되어있을시 서버통신
    private fun isAutoLogin() {
        CoroutineScope(Dispatchers.IO).launch {
            if (getHelper().get().autoLogin) {
                val requestData = SigninRequestData(
                    email = getHelper().get().id,
                    password = getHelper().get().password
                )
                try {
                    SignServiceCreator.signinService.signinLogin(requestData)
                    withContext(Dispatchers.Main) {
                        shortToast("자동 로그인")
                    }
                    val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    e.toString()
                }
            }
        }
    }
```
- SignInActivity 에서 자동로그인 로직 구현하기
```kotlin
      private fun autoLogin() {
        binding.ctAutoLogin.setOnClickListener {
            binding.ctAutoLogin.toggle()
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    getHelper().updateLogin(!binding.ctAutoLogin.isChecked)
                } catch (e: Exception) {
                    e.toString()
                }
            }
        }
    }
```
- 환경설정에서 자동로그인 해제하면 유저정보에 자동로그인 여부 업데이트 되게하였다.
```kotlin
        //앱을 처음 실행하는 경우가 아니면 온보딩 화면 안뜨게 함
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (getHelper().get().onBording == false) {
                    send()
                }
            } catch (e: Exception) {
                e.toString()
            }
        }
```
 - onBoardingActivity에서 온보딩 처음뜨는건지 아닌지 정보 가져와서 처음뜨는거 아니면 바로 SignInActivity로 가게 했다.
## ✅배운 것
- 7차 세미나때 배운건 거의 다 새롭게 알게 되었다.
- 대략적으로 알고있던 SharedPreferences랑 Room을 찍먹해봐서 좋았다.
- 배울게 천지다
</details> 
   
![github_김의진_ver1-10](https://user-images.githubusercontent.com/70698151/135753837-7997f154-ca2b-4b7a-bf51-a6fe3f29947f.png)


