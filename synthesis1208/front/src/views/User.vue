<template>
  <div class="home" style ="padding: 10px">
    <!-- 按钮-->
    <!-- 搜索-->


    <div style="margin: 10px 0;">
      <el-form inline="true" size="small">

        <el-form-item label="读者编号" >
          <el-input v-model="search1" placeholder="请输入读者编号"  clearable>
            <template #prefix><el-icon class="el-input__icon"><search/></el-icon></template>
          </el-input>
        </el-form-item >

        <el-form-item label="姓名" >
          <el-input v-model="search2" placeholder="请输入姓名"  clearable>
            <template #prefix><el-icon class="el-input__icon"><search /></el-icon></template>
          </el-input>
        </el-form-item >

        <el-form-item>
          <el-button type="primary" style="margin-left: 1%" @click="load" size="mini">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button size="mini"  type="danger" @click="clear">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!-- 按钮-->
    <!--    <div style="margin: 10px 0;" >
          <el-popconfirm title="确认删除?" @confirm="deleteBatch" v-if="user.role == 1">
            <template #reference>
              <el-button type="danger" size="mini" >批量删除</el-button>
            </template>
          </el-popconfirm>
        </div> -->

    <div style="margin: 10px 0">
      <!-- 数据字段-->
      <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"  @selection-change="handleSelectionChange" >

        <el-table-column prop="id" label="读者编号" sortable />
        <el-table-column prop="name" label="用户名" />
        <el-table-column prop="type" label="类型">
          <template v-slot="scope">
            <el-tag v-if="scope.row.type == true">用户</el-tag>
            <el-tag v-else >管理员</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="dept" label="部门" />
        <el-table-column prop="tel" label="电话号码" />

        <el-table-column fixed="right" label="操作" >
          <template v-slot="scope">

            <el-button  size="mini" @click ="handleEdit(scope.row)">编辑</el-button>

            <el-popconfirm title="确认删除?" @confirm="handleDelete(scope.row.id)">
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
      <el-dialog v-model="dialogVisible" title="编辑读者信息" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="用户名">
            <el-input style="width: 80%" v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="部门">
            <el-input style="width: 80%" v-model="form.dept"></el-input>
          </el-form-item>
          <el-form-item label="电话号码">
            <el-input style="width: 80%" v-model="form.tel"></el-input>
          </el-form-item>
          <el-form-item label="类型">
            <el-radio v-model="ftype" label="1">读者</el-radio>
            <el-radio v-model="ftype" label="2">管理员</el-radio>
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
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


export default {
  created(){
    this.load()

    // 这里得看你上个界面写进取的名字叫什么
    // 更改“admin
    let userStr = sessionStorage.getItem("user") ||"{}"
    this.user = JSON.parse(userStr)[0]
  },
  name: 'User',
  methods: {
    handleSelectionChange(val){
      this.ids = val.map(v => v.id)
    },

    load(){
      if(this.search1!='' & this.search2==''){
        request.get("/Users/getBySingleFilter",{
          params:{
            key:"id",
            value:this.search1,
          }
        }).then(res =>{
          console.log(res)
          this.tableData = res.data
          this.total = this.tableData.length
        })
      }
      else if (this.search1=='' & this.search2!=''){
        request.get("/Users/getBySingleFilter",{
          params:{
            key:"name",
            value:this.search2
          }
        }).then(res =>{
          console.log(res)
          this.tableData = res.data
          this.total = this.tableData.length
        })
      }
      else if(this.search1!='' & this.search2!=''){
        request.get("/Users/getByDoubleFilters",{
          params:{
            key1:"id",
            value1:this.search1,
            key2:"name",
            value2:this.search2
          }
        }).then(res =>{
          console.log(res)
          this.tableData = res.data
          this.total = this.tableData.length
        })
      }
      else{
        request.get("/Users/getAll",).then(res =>{
          this.tableData = res.data
          this.total = this.tableData.length
          console.log(this.total)
        })
      }
    },
    clear(){
      this.search1 = ""
      this.search2 = ""
      this.search3 = ""
      this.load()
    },

    handleDelete(id){
      request.get("/Users/deleteTuple",{
        params:{
          id:id,
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


    add(){
      this.dialogVisible= true
      this.form ={}
    },
    save(){
      request.get("/Users/updateTuple",{
        params:{
          oldId:this.form.id,
          id:this.form.id,
          name:this.form.name,
          type:this.form.type,
          password:this.form.password,
          dept:this.form.dept,
          tel:this.form.tel
        }
      }).then(res =>{
        console.log(res)
        if(res.code == 200){
          ElMessage({
            message: '更新成功',
            type: 'success',
          })
        }
        else {
          ElMessage.error(res.msg)
        }

        this.load() //不知道为啥，更新必须要放在这里面
        this.dialogVisible = false
      })

    },


    handleEdit(row){
      this.form = JSON.parse(JSON.stringify(row))
      console.log(this.form.type)
      if (this.ftype=="2"){
        this.form.type=0
      }
      else{
        this.form.type=1
      }
      this.dialogVisible = true
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
      dialogVisible : false,
      search1:'',
      search2:'',

      total:10,
      currentPage:1,
      pageSize: 10,
      tableData: [
      ],
      user:{},
      ids:[],
      ftype:'1'
    }
  },
}
</script>
