<template>
<div class="index">
    <el-container class="main_container">
      <el-header v-if="choose==1">学院借书排行</el-header>
	  <el-header v-if="choose==2">读者借书排行</el-header>
	  <el-header v-if="choose==3">出版社销量排行</el-header>
	  <el-header v-if="choose==4">最高产作者排行</el-header>
	  
      <el-main>
		  
		<div class="dept_rank" v-if="choose==1">
			<!--  deptName, bookCount, studentCount-->
			<el-table :data="tableData1.slice((currentPage-1)*pageSize,currentPage*pageSize)">
				<el-table-column prop="deptName" label="学院名称" />
				<el-table-column prop="bookCount" label="借阅书籍数目" sortable/>
				<el-table-column prop="studentCount" label="学生数目" />
			</el-table>
		
		</div>
		
		<div class="topUploadUser" v-if="choose==2">
			
			<!-- id as userId, name as userName, COUNT(recordId) as paperCount -->
			<el-table :data="tableData2.slice((currentPage-1)*pageSize,currentPage*pageSize)">
				<el-table-column prop="userId" label="用户id" />
				<el-table-column prop="userName" label="用户名" />
				<el-table-column prop="paperCount" label="借阅数" />
			</el-table>
		</div>
		
		<div class="topBuyPublisher" v-if="choose==3">
			
			<!-- publisherName, saleCount, publishCount -->
			<el-table :data="tableData3.slice((currentPage-1)*pageSize,currentPage*pageSize)">
				<el-table-column prop="publisherName" label="出版社" />
				<el-table-column prop="saleCount" label="销量" />
				<el-table-column prop="publishCount" label="一共出版的图书数" />
			</el-table>
		</div>
		
		<div class="topAuthor" v-if="choose==4">
			
			<!-- author,bookCount,paperCount,sum -->
			<el-table :data="tableData4.slice((currentPage-1)*pageSize,currentPage*pageSize)">
				<el-table-column prop="author" label="作者" />
				<el-table-column prop="bookCount" label="发表书籍" />
				<el-table-column prop="paperCount" label="发表文章" />
				<el-table-column prop="sum" label="发表总计" />
			</el-table>
		</div>
		
	  </el-main>
	  
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
	  
      <el-footer>
		  
			<el-button type="primary" size="large" @click.native="deptrank">学院借书排行</el-button>
			<el-button type="primary" size="large" @click.native="userrank">论文上传排行</el-button>
		    <el-button type="primary" size="large" @click.native="publisherrank">出版社销量排行</el-button>
		    <el-button type="primary" size="large" @click.native="authorrank">最高产作者排行</el-button>
		  
	  </el-footer>
    </el-container>
	

  </div>
</template>

<script>
import request from "../utils/request";
import {ElMessage} from "element-plus";

export default{
	created(){
		this.load()
		this.total=this.tableData1.length
	},
	name:"rank",
	data(){
		return{
			tableData1:[],
			tableData2:[],
			tableData3:[],
			tableData4:[],
			
			total:0,
			currentPage:1,
			pageSize: 10,
			
			choose:1,
		}
	},
	methods:{
		load(){
			request.get("/Stat/topBorrowDept",).then(res =>{
			  console.log(res)
			  this.tableData1 = res.data
			  // this.total = this.tableData1.length
			})
			
			request.get("/Stat/topUploadUser",).then(res =>{
			  console.log(res)
			  this.tableData2 = res.data
			  // this.total = this.tableData2.length
			})
			
			request.get("/Stat/topBuyPublisher",).then(res =>{
			  console.log(res)
			  this.tableData3 = res.data
			  // this.total = this.tableData1.length
			})
			
			request.get("/Stat/topAuthor",).then(res =>{
			  console.log(res)
			  this.tableData4 = res.data
			  // this.total = this.tableData1.length
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
		deptrank(){
			this.choose=1
			this.total=this.tableData1.length
		},
		userrank(){
			this.choose=2
			this.total=this.tableData2.length
		},
		publisherrank(){
			this.choose=3
			this.total=this.tableData3.length
		},
		authorrank(){
			this.choose=4
			this.total=this.tableData4.length
		}
	},
}	

</script>

<style scoped>


.el-footer {
  display: flex;

  align-items: center;
}

</style>