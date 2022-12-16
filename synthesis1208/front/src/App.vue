<template>
  <!-- <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </nav>
  <router-view/> -->
  
  
  <div style="overflow-y:hidden;overflow-x: hidden">
<!-- 	  <div id="nav">
		  <router-link to="/about">About</router-link> |
  		  <router-link to="/Book">Book</router-link> |
	</div> -->
  	<router-view />
  <!--    头部-->
  
    </div>
  
</template>

<style>
/* #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
} */



  export default {
    name:"App",

  }
</style>
