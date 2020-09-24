package com.example.parayo.inquiry

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.parayo.api.response.InquiryResponse
import com.example.parayo.common.paging.LiveDataPagedListBuilder
import com.example.parayo.inquiry.InquiryPagedAdapter.InquiryItemViewHolder
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.sdk27.coroutines.onClick

class InquiryPagedAdapter(
    private val inquiryItemClickListener: InquiryItemClickListener
) : PagedListAdapter<InquiryResponse, InquiryItemViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = InquiryItemViewHolder(parent, inquiryItemClickListener)

    override fun onBindViewHolder(
        holder: InquiryItemViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<InquiryResponse>() {
                override fun areItemsTheSame(
                    oldItem: InquiryResponse,
                    newItem: InquiryResponse
                ) = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: InquiryResponse,
                    newItem: InquiryResponse
                ) = oldItem == newItem
            }
    }

    class InquiryItemViewHolder(
        parent: ViewGroup,
        private val listener: InquiryItemClickListener,
        private val ui: InquiryItemUI = InquiryItemUI()
    ) : RecyclerView.ViewHolder(
        ui.createView(AnkoContext.create(parent.context, parent))
    ) {

        var inquiry: InquiryResponse? = null

        init {
            itemView.onClick { listener.onClickInquiry(inquiry) }
        }

        fun bind(item: InquiryResponse?) = item?.let {
            this.inquiry = item
            ui.productOwnerId = item.productOwnerId
            ui.requestUserName.text = item.requestUserName
            ui.productName.text = "상품명: ${item.productName}"
            ui.question.text = item.question
            ui.productOwnerName.text = item.productOwnerName
            ui.answer.text = item.answer
            ui.answerButton.onClick { listener.onClickAnswer(inquiry) }
            ui.invalidate()
        }

    }

    interface InquiryItemClickListener {
        fun onClickInquiry(inquiryResponse: InquiryResponse?)
        fun onClickAnswer(inquiryResponse: InquiryResponse?)
    }

    interface InquiryLiveDataBuilder :
        LiveDataPagedListBuilder<Long, InquiryResponse>

}