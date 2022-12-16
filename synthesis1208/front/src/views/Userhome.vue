<template>
  <div class="Userhomepage">
    <el-container>
      <!-- 显示学生信息,状态 -->

      <el-container>

        <!-- 侧边栏的功能 -->
        <el-aside width="150px">
          <el-row>
            <el-col>

              <el-menu
                  background-color="#545c64"
                  class="el-menu-vertical-demo"
                  text-color="#fff">

                <el-menu-item @click.native=" changepage(0)"> 借阅情况 </el-menu-item>

                <el-menu-item @click.native="changepage(1)"> 排行榜 </el-menu-item>

                <el-menu-item @click.native=" changepage(2)"> 图书查询 </el-menu-item>

                <el-menu-item @click.native=" changepage(3)"> 修改/上传图书信息 </el-menu-item>

                <el-menu-item @click.native=" changepage(4)"> 论文查询 </el-menu-item>

                <el-menu-item @click.native=" changepage(5)"> 上传论文 </el-menu-item>

                <el-menu-item @click.native=" changepage(6)"> 修改个人信息 </el-menu-item>

                <el-menu-item @click.native=" changepage(7)"> 修改个人密码 </el-menu-item>

                <el-menu-item v-if="this.type==2" @click.native=" changepage(8)"> 用户管理 </el-menu-item>

                <el-menu-item v-if="this.type==2" @click.native=" changepage(9)"> 借阅记录查询 </el-menu-item>
				
				<el-menu-item v-if="this.type==2" @click.native=" changepage(10)"> 购买记录查询 </el-menu-item>
				
				<el-menu-item v-if="this.type==2" @click.native=" changepage(11)"> 上传记录查询 </el-menu-item>

              </el-menu>
            </el-col>
          </el-row>
        </el-aside>

        <el-main>

          <div v-if="this.pagenum==0" class="lendrecord">
            <el-header align="center">
              <h1>借阅情况</h1>
            </el-header>
            <BookWithUser></BookWithUser>
          </div>
		  
        <div v-if="this.pagenum==1" class="rank">
                      <el-header align="center">
                        <h1>排行榜</h1>
                      </el-header>
                      <rank></rank>
                      <el-divider />
                    </div>

          <div v-if ="this.pagenum==2" class="booksearch">
            <h1 align="center">图书查询</h1>
            <p></p>
            <p></p>
            <Book></Book>
            <el-divider />
          </div>

          <div v-if="this.pagenum==3" class="bookupdate">

            <el-card style="width: 80%; margin-left: 20px; margin-top: 20px" >
              <h2 style="padding: 30px">修改上传图书信息</h2>
              <el-form :model="form_book" label-width="80px">
                <el-form-item label="书籍id" >
                  <el-input v-model="form_book.bid" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="书籍名称">
                  <el-input v-model="form_book.bname" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="出版年份" >
                  <el-input v-model="form_book.byear" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="类型" >
                  <el-input v-model="form_book.btype" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="作者" >
                  <el-input v-model="form_book.bauthor" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="出版商" >
                  <el-input v-model="form_book.bpublisher" autocomplete="off"></el-input>
                </el-form-item>

              </el-form>
              <div slot="footer" class="dialog-footer" style="text-align: center">
                <el-button type="primary" @click="Updatabook" >提交</el-button>
              </div>
            </el-card>
          </div>

          <div v-if="this.pagenum==4" class="thesissearch">
            <h1 align="center">论文查询</h1>
            <p></p>
            <p></p>
            <Thesis></Thesis>
            <el-divider />
          </div>

          <div v-if="this.pagenum==5" class="thesisupdate">

            <el-card style="width: 80%; margin-left: 20px; margin-top: 20px" >
              <h2 style="padding: 30px">上传论文信息</h2>
              <el-form :model="form_the" label-width="80px">
                <el-form-item label="论文id" >
                  <el-input v-model="form_the.tid" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="论文名称">
                  <el-input v-model="form_the.tname" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="发表年份">
                  <el-input v-model="form_the.tyear" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="类型">
                  <el-input v-model="form_the.ttype" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="作者">
                  <el-input v-model="form_the.tauthor" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="出版社" >
                  <el-input v-model="form_the.agent" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="页数" >
                  <el-input v-model="form_the.pnumber" autocomplete="off"></el-input>
                </el-form-item>

              </el-form>
              <div slot="footer" class="dialog-footer" style="text-align: center">
                <el-button type="primary" @click="Updatethesis" >提交</el-button>
              </div>
            </el-card>


          </div>

          <div v-if="this.pagenum==6" class="usrinfo">
            <Person></Person>
          </div>

          <div v-if="this.pagenum==7" class="changepass">
            <Password></Password>
          </div>

          <div  v-if="this.type==2 & this.pagenum==8" class="Usermanage">
            <h1 align="center">用户查询</h1>
            <p></p>
            <p></p>
            <User></User>
            <el-divider />
          </div>

          <div v-if="this.type==2 & this.pagenum==9"  class="lendrecord">
            <h1 align="center">借阅记录</h1>
            <p></p>
            <p></p>
            <LendRecord></LendRecord>>
            <el-divider />
          </div>
		  
		  <div v-if="this.type==2 & this.pagenum==10"  class="lendrecord">
		    <h1 align="center">图书购买记录</h1>
		    <p></p>
		    <p></p>
		    <Buys></Buys>
		    <el-divider />
		  </div>
		  
		  <div v-if="this.type==2 & this.pagenum==11"  class="lendrecord">
		    <h1 align="center">论文上传记录</h1>
		    <p></p>
		    <p></p>
		    <Uploads></Uploads>
		    <el-divider />
		  </div>


        </el-main>

      </el-container>

    </el-container>

  </div>
</template>

<script>

import Book from "./Book.vue"
import User from "./User.vue"
import Person from "./Person.vue"
import LendRecord from "./LendRecord.vue"
import {ElMessage} from "element-plus";
import router from "../router/index.js"
import { ref } from 'vue'
import { ElMessageBox } from 'element-plus'
import Password from "./Password.vue"
import BookWithUser from "./BookWithUser.vue"
import Thesis from "./Thesis.vue"
import request from "@/utils/request";
import rank from "./Rank.vue"
import Buys from "./Buys.vue"
import Uploads from "./Uploads"
import moment from "moment"
export default {
  created() {

    /*
      这里要从数据库的返回值获得用户的类型,这里认为是管理员
      最好是根据id,和密码把整个用户信息全部放进缓存
    */
    let userStr = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(userStr)[0]
    // console.log("userhome第一次读取:")
    // console.log(this.user)
    this.id = this.user.id
    this.type = (this.user.type? 1:  2)
    // console.log(this.user.type == false)
    // console.log(this.type)
  },

  name: "userhome",
  data() {
    return {
      pagenum: "0",

      form_book: {
        bid: '',
        bname: '',
        byear: '',
        btype: '',
        bauthor: '',
        bpublisher: ''
      },
      form_the: {
        tid: '',
        tname: '',
        tyear: '',
        ttype: '',
        tauthor: '',
        agent: '',
        pnumber:'',
      },

    }
  },
  components: {
    // Dashboard,
    Book,
    User,
    Person,
    LendRecord,
    Password,
    BookWithUser,
    Thesis,
	rank,
	Buys,
	Uploads
  },
  methods: {

    Updatabook() {
      request.get("/Books/newTuple",
          {
            params: {
              id: this.form_book.bid,
              name: this.form_book.bname,
              year: this.form_book.byear,
              type: this.form_book.btype,
              author: this.form_book.bauthor,
              publisher: this.form_book.bpublisher,
            }
          }).then(res => {
        console.log(res)
      }).catch(err => {
        console.log(err)
      })
    },

    changepage(index) {
      this.pagenum = index;
    },
    Updatethesis() {
      request.get("/Papers/newTuple",
          {
            params:{
              id:this.form_the.tid,
              name:this.form_the.tname,
              year:this.form_the.tyear,
              type:this.form_the.ttype,
              author:this.form_the.tauthor,
              publisher:this.form_the.agent,
              issueNo:this.form_the.tpublisher,
              pageNo:this.form_the.pnumber,
            }
          }).then(res =>{
        console.log(res)
      }).catch(err=>{
        console.log(err)
      })
	  var timestamp=new Date().getTime()
	  var time=moment().format("YYYY-MM-DDThh:mm:ss")
		request.get("/Uploads/newTuple",{
			params:{
				recordId:timestamp,
				paperId:this.form_the.tid,
				userId:this.user.id,
				dateTime:time
			}
		}).then(res=>{
			console.log(res)
		})
    }
  }
}
</script>

<style>
</style>