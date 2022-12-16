<template>
  <div class="home" style ="padding: 10px">

    <!-- 搜索-->
    <div style="margin: 10px 0;">
      <el-form inline="true" size="small">
		  
        <el-form-item label="图书编号" >
          <el-input v-model="search1" placeholder="请输入图书编号"  clearable>
            <template #prefix><el-icon class="el-input__icon"><search/></el-icon></template>
          </el-input>
        </el-form-item >

        <el-form-item label="读者编号" >
          <el-input v-model="search2" placeholder="请输入读者编号"  clearable>
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
	
<div style="margin: 10px 0">
    <!-- 数据字段-->

    <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)" stripe border="true" @selection-change="handleSelectionChange">
      <el-table-column v-if="user.type ==2"
                       type="selection"
                       width="55">
      </el-table-column>
	  <el-table-column prop="recordId" label="借阅编号" sortable />
      <el-table-column prop="bookId" label="图书编号" sortable />
      <el-table-column prop="bookname" label="图书名称" />
      <el-table-column prop="userId" label="读者编号" sortable/>
      <el-table-column prop="dateTime" label="借阅时间" sortable/>
      <el-table-column prop="returned" label="状态" >
        <template v-slot="scope">
          <el-tag v-if="scope.row.returned == false" type="warning">未归还</el-tag>
          <el-tag v-else type="success">已归还</el-tag>
        </template>
      </el-table-column>
      <el-table-column v-if="type == 2" label="操作" width="230px">
        <template v-slot="scope">
          <el-button  size="mini" @click ="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确认删除?" @confirm="handleDelete(scope.row)">
            <template #reference>
              <el-button type="danger" size="mini" >删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!--    分页-->
    
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


      <el-dialog v-model="dialogVisible" title="修改借阅记录" width="30%">
        <el-form :model="form" label-width="120px">

		<el-form-item label="借阅编号">
			<el-input style="width: 80%" v-model="form.recordId" disabled></el-input>
		</el-form-item>


          <el-form-item label="是否归还" prop="returned">
            <el-radio v-model="form.returned" label=0>未归还</el-radio>
            <el-radio v-model="form.returned" label="1">已归还</el-radio>
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

<script >

import request from "../utils/request";
import {ElMessage} from "element-plus";
import { defineComponent, reactive, toRefs } from 'vue'
import moment from "moment";
export default defineComponent({

  created(){
    this.load()
    let userStr = sessionStorage.getItem("user") ||"{}"
    this.user = JSON.parse(userStr)[0]
	this.type = (this.user.type ? 1 : 2)
  },
  name: 'LendRecord',
  methods: {
    handleSelectionChange(val){
      this.forms = val
    },
    load(){
      if(this.search1!=''&this.search2==''){
        request.get("/Borrows/getBySingleFilter1",{
          params:{
            key: "bookId",
            value: this.search1,
          }
        }).then(res =>{
          console.log(res)
          this.tableData = res.data
          this.total = res.data.length
        }).catch(err=>{
          console.log(err)
        })
      }
      else if(this.search1==''&this.search2!=''){
        request.get("/Borrows/getBySingleFilter1",{
          params:{
            key: "userId",
            value: this.search2,
          }

        }).then(res =>{
          console.log(res)
          this.tableData = res.data
          this.total = res.data.length
        }).catch(err=>{
          console.log(err)
        })
      }
      else if(this.search1!=''&this.search2!='')
      {
        request.get("/Borrows/getByDoubleFilters",{
          params:{
            key1: " bookId",
            value1: this.search1,
			key2: " userId",
			value2: this.search2,
          }

        }).then(res =>{
          console.log(res)
          this.tableData = res.data
          this.total = res.data.length
        }).catch(err=>{
          console.log(err)
        })
      }
	  else{
      request.get("/Borrows/getAll1",).then(res =>{
        console.log(res)
		    this.tableData = res.data
		    this.total = res.data.length
		  }).catch(err=>{
		    console.log(err)
		  })
	  }
    },
    save(){
		request.get("/Borrows/updateTuple",{
			params:{
				oldRecordId:this.form.recordId,
				recordId:this.form.recordId,
				bookId:this.form.bookId,
				userId:this.form.userId,
				dateTime:moment().format("YYYY-MM-DDThh:mm:ss"),
				returned:(this.form.returned=="1"?1:0)
			}
		}).then(res=>{
			if(res.code==200){
				ElMessage("修改成功")
			}
		})
		this.load()
      },
    clear(){
      this.search1 = ""
      this.search2 = ""
      this.search3 = ""
      this.load()
    },
    handleEdit(row){
      this.form = JSON.parse(JSON.stringify(row))
	  console.log(this.form)
      this.dialogVisible = true
    },
    handleSizeChange(pageSize){
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.currentPage = pageNum
      this.load()
    },
    handleDelete(row){
      
      request.get("/Borrows/deleteTuple",{
		  params:{
			  recordId:row.recordId
		  }
	  }).then(res =>{
        console.log(res)
        if(res.code == 200 ){
          ElMessage.success("删除成功")
        }
        else
          ElMessage.error(res.msg)
        this.load()
      })
    },
    add(){
      this.dialogVisible2 = true
      this.form ={}
    },

  setup() {
    const state = reactive({
      shortcuts: [
        {
          text: 'Today',
          value: new Date(),
        },
        {
          text: 'Yesterday',
          value: () => {
            const date = new Date()
            date.setTime(date.getTime() - 3600 * 1000 * 24)
            return date
          },
        },
        {
          text: 'A week ago',
          value: () => {
            const date = new Date()
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7)
            return date
          },
        },
      ],
      value1: '',
      value2: '',
      value3: '',
      defaultTime: new Date(2000, 1, 1, 12, 0, 0), // '12:00:00'
    })

    return toRefs(state)
  },
  },
  data() {
    return {
      form: {},
      search1:'',
      search2:'',
      search3:'',
      total:0,
      currentPage:1,
      pageSize: 10,
      tableData: [],
      user:{},
      dialogVisible : false,
      dialogVisible2: false,
	  type:1
	
    }
  },

})
</script>
