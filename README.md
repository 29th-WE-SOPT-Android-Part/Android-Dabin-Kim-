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
