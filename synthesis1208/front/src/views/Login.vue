<template>
<div  class="login-container"  >
    <el-form ref="form" :model="form"   :rules="rules" class="login-page">
      <h2 class="title" style="margin-bottom: 20px">系统登陆</h2>
	  
	  
      <el-form-item prop="username" >
        <el-input v-model="form.id"  clearable>
          <template #prefix>
            <el-icon class="el-input__icon"><User /></el-icon>
          </template>
        </el-input>
      </el-form-item>
	  
	  
      <el-form-item prop="password">
        <el-input v-model="form.password"  clearable show-password>
          <template #prefix>
            <el-icon class="el-input__icon"><Lock /></el-icon>
          </template>
        </el-input>
      </el-form-item>
	  
      <el-form-item >
        <el-button type="primary"  style=" width: 100%" @click="login">登 录</el-button>
      </el-form-item>
	  
      <el-form-item><el-button type="text" @click="$router.push('/register')">前往注册 >> </el-button></el-form-item>
    </el-form>
</div>

</template>

<script>
import request from "../utils/request";
import {ElMessage} from "element-plus";


export default {
  name: "Login",
  components:{
  },
  data() {
    return {
      form: {},
      rules: {
        id: [
          {
            required: true,
            message: '请输入读者证号',
            trigger: 'blur',
          }
        ],
        password: [
          {
            required: true,
            message: '请输入密码',
            trigger: 'blur',
          }
        ]
      }
    }
  },
  methods: {
    login() {
      /*
		
		这里要加上后端的判断!!!
		
		*/

      console.log(this.form)
      sessionStorage.setItem("user",JSON.stringify(this.form))//缓存用户信息
      this.user = JSON.parse(sessionStorage.getItem("user") ||"{}")

      request.get("/Users/getBySingleFilter", {
        params: {
          key: "name",
          value: this.user.id,
        }
      }).then(res => {
        ElMessage.success("登录成功")
        sessionStorage.setItem("user", JSON.stringify(res.data))//缓存用户信息
        console.log(sessionStorage.getItem("user") ||"{}")
        this.$router.push("/UHome")
      }).catch(err => {
        console.log(err)
      })
    }
  }
}

</script>

<style scoped>
.login-container {
  position: fixed;
  width: 100%;
  height: 100vh;
}
.login-page {
  border-radius: 5px;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
</style>