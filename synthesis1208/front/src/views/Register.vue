<template>
<div  class="login-container"  >
    <el-form ref="form" :model="form"    :rules="rules" class="login-page">
      <h2 class="title" style="margin-bottom: 20px">用户注册</h2>
	  
		<el-form-item prop="id" >
			<el-input v-model="form.id" placeholder="请输入读书证号" clearable >
				<template #prefix>
					<el-icon class="el-input__icon"><User/></el-icon>
				</template>
			</el-input>
		</el-form-item>
		  
		<el-form-item prop="name" >
			<el-input v-model="form.name" placeholder="请输入姓名" clearable >
				<template #prefix>
					<el-icon class="el-input__icon"><User/></el-icon>
				</template>
			</el-input>
		</el-form-item>
		  
		<el-form-item prop="type">
			<el-radio v-model="form.type" label="1">读者</el-radio>
			<el-radio v-model="form.type" label="0">管理员</el-radio>
		</el-form-item>
		  
		  
		<el-form-item prop="password">
			<el-input v-model="form.password"  placeholder="请输入密码" clearable show-password>
				<template #prefix>
					<el-icon class="el-input__icon"><Lock /></el-icon>
				</template>
			</el-input>
		</el-form-item>
		  
		<el-form-item prop="confirm">
			<el-input v-model="confirm" placeholder="请再次确认密码" clearable show-password>
				<template #prefix>
					<el-icon class="el-input__icon"><Lock /></el-icon>
				</template>
			</el-input>
		</el-form-item>
		
		<el-form-item prop="dept">
			<el-input v-model="form.dept" placeholder="请输入学院" clearable>
				<template #prefix>
					<el-icon class="el-input__icon"><User /></el-icon>
				</template>
			</el-input>
		</el-form-item>
		
		<el-form-item prop="tel">
			<el-input v-model="form.tel" placeholder="请输入电话" clearable>
				<template #prefix>
					<el-icon class="el-input__icon"><User /></el-icon>
				</template>
			</el-input>
		</el-form-item>
	  

		<el-form-item >
			<el-button type="primary" style=" width: 100%" @click="register">注 册</el-button>
		</el-form-item>
	  
		<el-form-item><el-button type="text" @click="$router.push('/')">前往登录>> </el-button></el-form-item>
    </el-form>
</div>

</template>

<script>
import request from "../utils/request";
import {ElMessage} from "element-plus";

export default {
  name: "Register",
  components:{
  },
  data(){
    return{
		confirm:"",
		form:{},
	  
		rules: {
		id:[
			{
				required: true,
				message: '请输入借书证号',
				trigger: 'blur',
			}
		],
		name:[
			{
				required: true,
				message: '请输入姓名',
				trigger: 'blur',
			}
		],
		type:"1",
		password: [
		  {
		    required: true,
		    message: '请输入密码',
		    trigger: 'blur',
		  }
		],
		dept:[
		  {
		    required:true,
		    message:"请输入学院",
		    trigger:"blur"
		  }
		],
		tel:[
		  {
		    required:true,
		    message:"请输入电话",
		    trigger:"blur"
		  }
		],
        
      }
    }
    },

  methods:{

    register(){
		console.log(this.form)
		  if(this.form.password != this.confirm)
		  {
			ElMessage.error("两次密码输入不一致")
			return
		  }
		  
		  /*
		  这里要连接数据库，把账号密码写进去
		  */
		  request.get("/Users/newTuple",{
			  params:{
			  id:this.form.id,
			  name:this.form.name,
			  type:this.form.type,
			  password:this.form.password,
			  dept:this.form.dept,
			  tel:this.form.tel}
		  }).then(res=>{
			console.log(res)
			if(res.code == 200)
			{
			  ElMessage.success("注册成功")
			  this.$router.push("/")
			}
			else {ElMessage.error(res.msg)}
        })

    }
  }

  }

</script>

<style scoped>
.login-container {
  position: fixed;
  width: 100%;
  height: 100%;
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