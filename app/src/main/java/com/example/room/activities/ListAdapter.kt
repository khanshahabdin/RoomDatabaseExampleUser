package com.example.room.activities


import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.buildSpannedString
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.databinding.ActivityUserlistBinding
import com.example.room.databinding.ItemMemberBinding
import com.example.room.user
import cn.pedant.SweetAlert.SweetAlertDialog
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener


class ListAdapter(
    val context: Context,
    val deleteOnLongClick: (user)
    -> Unit
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemMemberBinding) : RecyclerView.ViewHolder(binding.root)

    private var data: List<user> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = data[position]

        holder.binding.tvMid.text = currentItem.id.toString()
        holder.binding.uName.text = currentItem.name
        holder.binding.uEmailaddress.text = currentItem.email
        holder.binding.uContactNumber.text = currentItem.mobile




        holder.binding.manlay.setOnClickListener {


            /*val dailog = AlertDialog.Builder(context)
            dailog.setTitle("User")
            dailog.setMessage("Chose one option")
            dailog.setPositiveButton("Delete") { dg, _ ->
                deleteOnLongClick(currentItem)
                dg.dismiss()*/



            SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Do you want to edit or delete user?")
                .setConfirmText("Delete!")
                .setConfirmClickListener { sDialog -> sDialog.dismissWithAnimation()


                    SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Won't be able to recover this User!")
                        .setConfirmText("Yes,delete it!")
                        .setConfirmClickListener { sDialog ->
                            sDialog
                                .setTitleText("Deleted!")
                                .setContentText("Your User has been deleted!")
                                .setConfirmText("OK")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE)
                            deleteOnLongClick(currentItem)
                        }
                        .show()

                }
                .setCancelButton(
                    "Edit"
                ) {


                        sDialog -> sDialog.dismissWithAnimation()
                    val intent = Intent(context, updateActivity::class.java)
                    intent.putExtra("id", currentItem.id.toString())
                    intent.putExtra("name", currentItem.name)
                    intent.putExtra("email", currentItem.email)
                    intent.putExtra("mob", currentItem.mobile)
                    context.startActivity(intent)
                }
                .show()



               /* val customDialog = Dialog(context)
                customDialog.setContentView(R.layout.custom_dialog_fragment)
                customDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                val yesBtn = customDialog.findViewById(R.id.yes_opt) as TextView
                val noBtn = customDialog.findViewById(R.id.no_opt) as TextView
                yesBtn.setOnClickListener {
                    //Do something here
                    deleteOnLongClick(currentItem)

                    customDialog.dismiss()
                }
                noBtn.setOnClickListener {

                    val intent = Intent(context, updateActivity::class.java)
                    intent.putExtra("id", currentItem.id.toString())
                    intent.putExtra("name", currentItem.name)
                    intent.putExtra("email", currentItem.email)
                    intent.putExtra("mob", currentItem.mobile)
                    context.startActivity(intent)
                    customDialog.dismiss()
                }
                customDialog.show()
*/



            /*dailog.setNegativeButton("Edit") { _, _ ->
                val intent = Intent(context, updateActivity::class.java)
                intent.putExtra("id", currentItem.id.toString())
                intent.putExtra("name", currentItem.name)
                intent.putExtra("email", currentItem.email)
                intent.putExtra("mob", currentItem.mobile)
                context.startActivity(intent)

            }
            dailog.show()
*/
        }
    }

    override fun getItemCount() = data.size

    fun setData(list: List<user>) {
        data = list
        notifyDataSetChanged()
    }


}
/* private var userList = emptyList<user>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_member,false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       *//*val currentItem = userList[position]
        holder.itemView.uid.text = currentItem.id.toString()
        holder.itemView.firstname.text = currentItem.firstName
        holder.itemView.lastname.text = currentItem.lastName
        holder.itemView.age.text = currentItem.age.toString()

        holder.itemView.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }*//*
    }
    fun setData(user: List<user>){
        this.userList = user
        notifyDataSetChanged()

    }
}*/