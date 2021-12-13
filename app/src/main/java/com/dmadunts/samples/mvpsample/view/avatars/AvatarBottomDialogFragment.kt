package com.dmadunts.samples.mvpsample.view.avatars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.dmadunts.samples.mvpsample.databinding.LayoutAvatarBottomSheetBinding
import com.dmadunts.samples.mvpsample.model.Avatar
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AvatarBottomDialogFragment : BottomSheetDialogFragment(), AvatarAdapter.AvatarListener {
    private var _binding: LayoutAvatarBottomSheetBinding? = null
    private val binding get() = _binding!!
    private lateinit var callback: AvatarAdapter.AvatarListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutAvatarBottomSheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.avatarRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.avatarRecyclerView.adapter = AvatarAdapter(AvatarStore.AVATARS, this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        try {
            callback = activity as AvatarAdapter.AvatarListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement com.dmadunts.samples.mvpsample.view.avatars.AvatarAdapter.AvatarListener")
        }
    }

    override fun avatarClicked(avatar: Avatar) {
        callback.avatarClicked(avatar)
    }

    companion object {
        fun newInstance(): AvatarBottomDialogFragment {
            return AvatarBottomDialogFragment()
        }
    }
}