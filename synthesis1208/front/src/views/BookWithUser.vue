<template>
  <div class="home" style ="padding: 10px">

    <!--    分页-->
    <div style="margin: 10px 0">
      <!-- 数据字段-->
      <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)" @selection-change="handleSelectionChange">

        <el-table-column prop="recordId" label="借阅编号" sortable />
        <el-table-column prop="bookId" label="图书编号" />
		<el-table-column prop="bookname" label="图书名称" />
        <el-table-column prop="userId" label="用户编号" />
        <el-table-column prop="dateTime" label="到期时间" sortable/>
        <el-table-column prop="returned" label="是否归还">
          <template v-slot="scope">
            <el-tag v-if="scope.row.returned != true" type="warning">未还书</el-tag>
            <el-tag v-else type="success">已还书</el-tag>
          </template>
        </el-table-column>

        <el-table-column fixed="right" label="操作" >

          <template v-slot="scope">
            <el-button  size="mini" @click ="handleEdit(scope.row)" v-if="scope.row.returned==true" >续借</el-button>
            <el-button  size="mini" @click ="handlereturn(scope.row)" v-if="scope.row.returned!=true" >还书</el-button>
            <el-popconfirm title="确认删除?" @confirm="handleDelete(scope.row) ">
              <template #reference>
                <el-button type="danger" size="mini" >删除</el-button>
              </template>
            </el-popconfirm>

          </template>

        </el-table-column>
      </el-table>
      <el-pagination
          v-model:currentPage="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>

    <div style="margin: 10px 0">
      <el-dialog v-model="dialogVisible2" title="修改借阅信息" width="30%">
        <el-form :model="form" label-width="120px">

          <el-form-item label="借阅编号">
            <el-input style="width: 80%" v-model="form.recordId"></el-input>
          </el-form-item>
          <el-form-item label="图书编号">
            <el-input style="width: 80%" v-model="form.bookId"></el-input>
          </el-form-item>

          <el-form-item label="借阅者">
            <el-input style="width: 80%" v-model="form.userId"></el-input>
          </el-form-item>


<!--          <el-form-item label="借阅状态">
            <el-input style="width: 80%" v-model="form.returned"></el-input>
          </el-form-item> -->
        </el-form>
        <template #footer>
	<span class="dialog-footer">
	  <el-button @click="dialogVisible2 = false">取 消</el-button>
	  <el-button type="primary" @click="save">确 定</el-button>
	</span>
        </template>
      </el-dialog>

    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import request from "../utils/request";
import {ElMessage,ElPagination} from "element-plus";
import moment from "moment";
export default {
  created(){
    let userStr = sessionStorage.getItem("user") ||"{}"
    this.user = JSON.parse(userStr)[0]
    this.load()
  },
  name: 'bookwithuser',
  methods: {

    handleSelectionChange(val){
      this.forms = val
    },

    load(){
      request.get("/Borrows/getBySingleFilter1",{
        params:{
          key: "userId",
          value:this.user.id
        }

      }).then(res =>{
        console.log(res)
        this.tableData = res.data
        this.total = res.data.length
      }).catch(err=>{
        console.log(err)
      })
    },

    handleDelete(row){
      console.log(row)
      request.get("/Borrows/deleteTuple",{
        params:{
          recordId:row.recordId,
        }
      }).then(res =>{
        // console.log(res)
        if(res.data == "已删除" ){
          ElMessage.success("删除成功")
        }
        else
          ElMessage.error(res.data)
        this.load()
      })
    },

    save(){
      //ES6语法
      //地址,但是？IP与端口？+请求参数
      // this.form?这是自动保存在form中的，虽然显示时没有使用，但是这个对象中是有它的
      console.log(this.form)
	  var time=moment().add(1,'weeks').format("YYYY-MM-DDThh:mm:ss")
      request.get("/Borrows/updateTuple",{
        params:{
          oldRecordId:this.form.recordId,
          recordId:this.form.recordId,
          bookId:this.form.bookId,
          userId:this.form.userId,
          dateTime:time,
          returned:0
        }
      }).then(res =>{
        console.log(res)
        if(res.code == 0){
          ElMessage({
            message: '修改信息成功',
            type: 'success',
          })
        }
        else {
          ElMessage.error(res.msg)
        }
        this.load()
        this.dialogVisible2 = false
      })
    },

    handleEdit(row){
      this.form = JSON.parse(JSON.stringify(row))
      console.log(this.form)
      this.dialogVisible2 = true
    },
    handlereturn(row){
		console.log(row)
      request.get("/Borrows/updateTuple",{
        params:{
          oldRecordId:row.recordId,
          recordId:row.recordId,
          bookId:row.bookId,
          userId:row.userId,
          dateTime:moment().format("YYYY-MM-DDThh:mm:ss"),
          returned:1
        }
      }).then(res =>{
        console.log(res)
        if(res.code == 200){
          ElMessage({
            message: '修改信息成功',
            type: 'success',
          })
        }
        else {
          ElMessage.error(res.msg)
        }
        this.load()
        this.dialogVisible2 = false
      })
    },
    handleSizeChange(newSize){
      this.pageSize = newSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.currentPage = pageNum
      this.load()
    },

  },
  data() {
    return {
      form: {},

      dialogVisible: false,
      dialogVisible2: false,

      total:10,
      currentPage:1,
      pageSize: 10,
      tableData: [],

      user:{},
      forms:[],
    }
  },
}
</script>
