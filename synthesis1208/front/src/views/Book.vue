<template>
  <div class="home" style ="padding: 10px">

    <!-- 搜索-->
    <div style="margin: 10px 0;">

      <!--
      ！！！！！！！！！！！！！！！！！！！！！

      最后要删掉！！！！
      因为只有俩查询条件而且读者很可能不知道图书编号
      -->
      <el-form inline="true" size="small">
        <!--        <el-form-item label="图书编号" >
                  <el-input v-model="search1" placeholder="请输入图书编号"  clearable>
                    <template #prefix><el-icon class="el-input__icon"><search/></el-icon></template>
                  </el-input>
                </el-form-item > -->

        <el-form-item label="图书名称" >
          <el-input v-model="search2" placeholder="请输入图书名称"  clearable>
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
        <el-form-item style="float: right" v-if="numOfOutDataBook!=0">
          <el-popconfirm
              confirm-button-text="查看"
              cancel-button-text="取消"
              :icon="InfoFilled"
              icon-color="red"
              title="您有图书已逾期，请尽快归还"
              @confirm="toLook"
          >
            <template #reference>
              <el-button  type="warning">逾期通知</el-button>
            </template>
          </el-popconfirm>
        </el-form-item>
      </el-form>
    </div>

    <!-- 数据字段-->
    <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)" stripe border="true" @selection-change="handleSelectionChange">4

      <el-table-column prop="id" label="图书编号" sortable />
      <el-table-column prop="name" label="图书名称" />
      <el-table-column prop="author" label="作者" />
      <el-table-column prop="publisher" label="出版社" />
      <el-table-column prop="year" label="出版时间" sortable/>
      <el-table-column prop="type" label="种类" />

      <el-table-column prop="type" label="状态">
        <template v-slot="scope">
          <el-tag v-if="scope.row.recordId !=0 " type="warning">已借阅</el-tag>
          <el-tag v-else type="success">未借阅</el-tag>
        </template>
      </el-table-column>

      <el-table-column fixed="right" label="操作">
        <template v-slot="scope">
          <!-- 只有管理员才能决定是否删除 -->
          <!-- 在里面加上v-if="type==2" -->
          <el-button  size="mini" @click ="handleEdit(scope.row)" v-if="type==2">修改</el-button>

          <el-popconfirm title="确认删除?" @confirm="handleDelete(scope.row.id)" >

            <template #reference>
              <el-button type="danger" size="mini" v-if="type==2">删除</el-button>
            </template>
          </el-popconfirm>

          <el-button  size="mini" @click ="handlelend(scope.row)" v-if="scope.row.recordId == 0 & type==1" >借阅</el-button>
		  <el-button  size="mini" @click ="handlebuy(scope.row)" v-if="scope.row.recordId == 0 & type==1" >购买</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--测试,通知对话框-->
    <!-- 修改对话框 -->
    <el-dialog
        v-model="dialogVisible2"
        v-if="type==2"
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
      </el-form>
      <template #footer>
	  <span class="dialog-footer">
	    <el-button type="primary" @click="save">确认修改</el-button>
	  </span>
      </template>
    </el-dialog>

    <el-dialog
        v-model="dialogVisible3"
        v-if="numOfOutDataBook!=0"
        title="逾期详情"
        width="50%"
        :before-close="handleClose"
    >
      <el-table :data="outDateBook" style="width: 100%">
        <el-table-column prop="bid" label="图书编号" />
        <el-table-column prop="bname" label="书名" />
        <el-table-column prop="lendtime" label="借阅日期" />
        <el-table-column prop="deadtime" label="截至日期" />
      </el-table>

      <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="dialogVisible3 = false"
        >确认</el-button>
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
    console.log(this.user)
    this.load()
    this.type = (this.user.type ? 1 : 2)
  },
  bname: 'Book',
  methods: {
    // (this.bidArray.indexOf(scope.row.bid)) == -1
    handleSelectionChange(val){
      this.ids = val.map(v =>v.id)
    },
    load(){
      this.numOfOutDataBook =0;
      this.outDateBook =[];
      if(this.search2!='' & this.search3==''){
        request.get("/Books/getBySingleFilter1",{
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
      else if(this.search2=='' & this.search3!=''){
        request.get("/Books/getBySingleFilter1",{
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
      else if(this.search2!='' & this.search3!='')
      {
        request.get("/Books/getByDoubleFilters",{
          params:{
            key1: "id",
            value1: this.search2,
            key2:"name",
            value2:this.search3
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
        request.get("/Books/getAll").then(res =>{
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
      request.get("/Books/deleteTuple",{
        params:{
          recordId:id,
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

    handlelend(row){
      var timestamp=new Date().getTime()
	  var time=moment().add(1,'weeks').format("YYYY-MM-DDThh:mm:ss")
	  console.log(time)
	  console.log(this.user.id)
            request.get("/Borrows/newTuple",{
              params:{
                recordId:timestamp,
                bookId:row.id,
                userId:this.user.id,
                dateTime:time,
				//dateTime:this.time(timestamp),
                returned:0,
              }
            }).then(res=>{
              console.log(res)
            })
        this.load()

      // request.get("/Borrows/getByDoubleFilters",{
      //   params:{
      //     key1:"bookId",
      //     value1:row.id,
      //     key2:"returned",
      //     value2:false,
      //   }
      // }).then(res =>{
      //   console.log(res)
      //   request.get("/Borrows/updateTuple",{
      //     params:{
      //       oldRecordId:,
      //       recordId:
      //       bookId:,
      //       userId:,
      //       dateTime:,
      //       returned:
      //     }
      //   })
      //   this.load()
      // })
    },
	handlebuy(row){
	  var timestamp=new Date().getTime()
	  var time=moment().format("YYYY-MM-DDThh:mm:ss")
	  console.log(time)
	  console.log(this.user.id)
	        request.get("/Buys/newTuple",{
	          params:{
	            recordId:timestamp,
	            bookId:row.id,
	            userId:this.user.id,
	            dateTime:time,
				//dateTime:this.time(timestamp),
	          }
	        }).then(res=>{
				ElMessage("已购买")
	          console.log(res)
	        })
	    this.load()
	},
    add(){
      this.dialogVisible= true
      this.form ={}
    },
    save(){
      //ES6语法
      //地址,但是？IP与端口？+请求参数
      // this.form?这是自动保存在form中的，虽然显示时没有使用，但是这个对象中是有它的
      request.get("/Books/updateTuple",{
        params:{
          oldId:this.form.id,
          id:this.form.id,
          name:this.form.name,
          year:this.form.year,
          type:this.form.type,
          author:this.form.author,
          publisher:this.form.publisher,
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
      c:1,
      form: {},
      form2:{},
      form3:{},
      dialogVisible: false,
      dialogVisible2: false,
      search1:'',
      search2:'',
      search3:'',
      total:10,
      currentPage:1,
      pageSize: 10,
      tableData: [],
      user:{},
      number:0,
      bookData:[],
      bidArray:[],
      outDateBook:[],
      numOfOutDataBook: 0,
      dialogVisible3 : true,
    }
  },
}
</script>
