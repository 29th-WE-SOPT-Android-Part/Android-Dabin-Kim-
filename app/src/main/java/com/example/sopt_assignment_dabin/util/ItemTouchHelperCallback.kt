package com.example.sopt_assignment_dabin.util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(val listener: ItemTouchHelperListener): ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN  //드래그되는 방향 설정
        val swipeFalgs = ItemTouchHelper.START or ItemTouchHelper.END //스와이프되는 방향 설정
        return makeMovementFlags(dragFlags, swipeFalgs)
    }

    //아이템을 길게 누르면 Drag & Drop 작업을 시작해야 하는지를 반환한다. 디폴트는 true
    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    //아이템이 Drag 되면 ItemTouchHelper는 onMove()를 호출한다. 이때 ItemActionListener로 어댑터에
    // fromPosition과 toPosition을 파라미터와 함께 콜백을 전달한다.
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return listener.onItemMove(viewHolder!!.adapterPosition, target!!.adapterPosition)
    }

    //아이템이 Swipe 되면 ItemTouchHelper는 범위를 벗어날 때까지 애니메이션을 적용한 후 onSwiped()를 호출한다.
    //이때 ItemActionListener로 어댑터에 제거할 아이템의 position을 파라미터와 함께 콜백을 전달한다.
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onItemSwipe(viewHolder!!.adapterPosition)
    }
}

