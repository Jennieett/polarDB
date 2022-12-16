<template>
  <div class="home" style ="padding: 10px">

    <!-- 搜索-->
    <div style="margin: 10px 0;">
      <el-form inline="true" size="small">
        <el-form-item label="论文编号" >
          <el-input v-model="search1" placeholder="请输入论文编号"  clearable>
            <template #prefix><el-icon class="el-input__icon"><search/></el-icon></template>
          </el-input>
        </el-form-item >
        <el-form-item label="论文名称" >
          <el-input v-model="search2" placeholder="请输入论文名称"  clearable>
            <template #prefix><el-icon class="el-input__icon"><search /></el-icon></template>
          </el-input>
        </el-form-item >
        <el-form-item label="作者" >
          <el-input v-model="search3" placeholder="请输入作者"  clearable>
            <template #prefix><el-icon class="el-input__icon"><search /></el-icon></template>
          </el-input>
        </el-form-item >
        <el-form-item>
          <el-button type="primary" style="margin-left: 1%" @click="load" size="mini" >
            <svg-icon iconClass="search"/>查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button size="mini"  type="danger" @click="clear">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!-- 数据字段-->
    <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)" stripe border="true" @selection-change="handleSelectionChange">
      <el-table-column v-if="user.type ==2"
                       type="selection"
                       width="55">
      </el-table-column>
      <el-table-column prop="id" label="论文编号" sortable />
      <el-table-column prop="name" label="论文名称" />
      <el-table-column prop="author" label="作者" />
      <el-table-column prop="publisher" label="出版社" />
      <el-table-column prop="year" label="出版时间" sortable/>
      <el-table-column prop="pageNo" label="页数" />
      <el-table-column fixed="right" label="操作" >
        <template v-slot="scope">
          <el-button  size="mini" @click ="handleEdit(scope.row)" v-if="user.type==2">修改</el-button>

          <el-popconfirm title="确认删除?" @confirm="handleDelete(scope.row.id)" v-if="user.type == 2">
            <template #reference>
              <el-button type="danger" size="mini" >删除</el-button>
            </template>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>


    <!-- 修改对话框 -->
    <el-dialog
        v-model="dialogVisible2"
        v-if="user.type==2"
        title="修改信息"
        width="50%"
        :before-close="handleClose">
      <el-form :model="form" label-width="120px">
        <el-form-item label="书名">
          <el-input style="width: 80%" v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="出版年份">
          <el-input style="width: 80%" v-model="form.year"></el-input>
        </el-form-item>
        <el-form-item label="类型">
          <el-input style="width: 80%" v-model="form.type"></el-input>
        </el-form-item>
        <el-form-item label="作者">
          <el-input style="width: 80%" v-model="form.author"></el-input>
        </el-form-item>
        <el-form-item label="出版商">
          <el-input style="width: 80%" v-model="form.publisher"></el-input>
        </el-form-item>
        <el-form-item label="出版商">
          <el-input style="width: 80%" v-model="form.issueNo"></el-input>
        </el-form-item>
        <el-form-item label="页数">
          <el-input style="width: 80%" v-model="form.pageNo"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
	  <span class="dialog-footer">
	    <el-button type="primary" @click="save">确认修改</el-button>
	  </span>
      </template>
    </el-dialog>

    <!--    分页-->
    <div style="margin: 10px 0">
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
  </div>


</template>

<script>
// @ is an alias to /src
import request from "../utils/request";
import {ElMessage} from "element-plus";
import moment from "moment";
export default {
  created(){
    let userStr = sessionStorage.getItem("user") ||"{}"
    /*
    这里user也是含所有信息的User
    */
    this.user = JSON.parse(userStr)[0]
    this.user.type=2
    console.log(this.user)
    this.load()
  },
  name: 'Thesis',
  methods: {
    // (this.tidArray.indexOf(scope.row.tid)) == -1
    handleSelectionChange(val){
      this.ids = val.map(v =>v.id)
    },
    deleteBatch(){
      if (!this.ids.length) {
        ElMessage.warning("请选择数据！")
        return
      }
      //  一个小优化，直接发送这个数组，而不是一个一个的提交删除
      request.post("/book/deleteBatch",this.ids).then(res =>{
        if(res.code === '0'){
          ElMessage.success("批量删除成功")
          this.load()
        }
        else {
          ElMessage.error(res.msg)
        }
      })
    },
    load(){
      this.numOfOutDataBook =0;
      this.outDateBook =[];
      //这里是论文
      if(this.search1!=''){
        request.get("/Papers/getBySingleFilter",{
          params:{
            key: "id",
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
      else if(this.search2!=''){
        request.get("/Papers/getBySingleFilter",{
          params:{
            key: "name",
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
      else if(this.search3!='')
      {
        request.get("/Papers/getBySingleFilter",{
          params:{
            key: "author",
            value: this.search3,
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
		  request.get("/Papers/getAll",).then(res =>{
		    console.log(res)
		    this.tableData = res.data
		    this.total = res.data.length
		  }).catch(err=>{
		    console.log(err)
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
      request.get("/Papers/deleteTuple",{
        params:{
          id:id
        }
      }).then(res =>{
        console.log(res)
        if(res.code == 0 ){
          ElMessage.success("删除成功")
        }
        else
          ElMessage.error(res.msg)
        this.load()
      })
    },
    add(){
      this.dialogVisible= true
      this.form ={}
    },
    save(){
      request.get("/Papers/updateTuple",{
        params:{
          oldId:this.form.id,
          id:this.form.id,
          name:this.form.name,
          year:this.form.year,
          type:this.form.type,
          author:this.form.author,
          publisher:this.form.publisher,
          issueNo:this.form.issueNo,
          pageNo:this.form.pageNo,
        }
      }).then(res =>{
        // console.log(res)
        if(res.data == "已修改" ){
          ElMessage.success("修改成功")
        }
        else
          ElMessage.error(res.data)
        this.load()
      })
    },
    // formatter(row) {:formatter="formatter"
    //   return row.address
    // },

    handleEdit(row){
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible2 = true
      this.form.id=row.id
    },
    handleSizeChange(pageSize){
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.currentPage = pageNum
      this.load()
    },
    toLook(){
      this.dialogVisible3 =true;
    },
  },
  data() {
    return {
      form: {},
      form2:{},
      form3:{},
      dialogVisible: false,
      dialogVisible2: false,
      search1:'',
      search2:'',
      search3:'',
      total:0,
      currentPage:1,
      pageSize: 10,
      tableData: [],
      user:{},
      number:0,
      bookData:[],
      tidArray:[],
      outDateBook:[],
      numOfOutDataBook: 0,
      dialogVisible3 : true,
    }
  },
}
</script>
