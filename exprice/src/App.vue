<template>
  <div id="app">
    <img src="./assets/logo.png">
    <router-view/>
    <router-link :to="{path:'appler/redApple'}" >to apple</router-link>
    {{totalPrice}}
    <Apple></apple>
    <Banana></Banana>
  </div>
</template>

<script>


export default {
  components: {},
  computed:{
    totalPrice(){
      return this.$store.state.totalPrice
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
