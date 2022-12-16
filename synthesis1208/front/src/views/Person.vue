<template>
  <div>
    <el-card style="width: 100%; margin-left: 0px; margin-top: 0px" >
      <h2 style="padding: 30px">个人信息</h2>
      <el-form :model="form" ref="form" label-width="30%">

        <el-form-item label="账号">
          <el-input style="width: 80%" v-model="form.id" disabled></el-input>
        </el-form-item>

        <el-form-item label="姓名">
          <el-input style="width: 80%" v-model="form.name"></el-input>
        </el-form-item>

        <el-form-item label="部门">
          <el-input style="width: 80%" v-model="form.dept"></el-input>
        </el-form-item>

        <el-form-item label="电话号码">
          <el-input style="width: 80%" v-model="form.tel"></el-input>
        </el-form-item>


      </el-form>
      <div style="text-align: center">
        <el-button type="primary" @click="update">保存</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
import request from "@/utils/request";
import {ElMessage} from "element-plus";
import Password from "./Password.vue"
export default {
  name: "Person",
  data() {
    return {
      form: {},
    }
  },
  created() {

    /*
    这里的getItem要换成从数据库中获得的一套数据
    */
    let str = sessionStorage.getItem("user") || "{}"
    this.form = JSON.parse(str)[0]
    console.log(this.form)
  },
  methods: {
    update() {
      request.get("/Users/updateTuple",{
        params:{
          oldId:this.form.id,
          id:this.form.id,
          name:this.form.name,
          type:this.form.type?1:0,
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

        this.dialogVisible = false
      })
    },
    save(){

    },
  }
}
</script>

<style>
.avatar-uploader  {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader:hover {
  border-color: #409EFF;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.box-card {
  width: 60%;
  margin: auto;
  padding: 20px;
}
</style>
