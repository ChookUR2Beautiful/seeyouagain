<template>
  <div class="slide-show">
     <div >
        <div class="slide-img">
          <a :href="slides[slidesIndex].src" @onmouseover="clearup()" @onmouseout="dingshifun()" >
            <img :src="slides[slidesIndex].src">
          </a>
        </div>
     </div>
     <h2>title</h2>
     <ul class="slide-pages">
        <li @click="left(-1)">&lt;</li>
        <li  v-for="(item,index) in slides">
          <a :class="{on: index === nowIndex}" @click="goto(index)">{{index+1}}</a>
        </li>
        <li @click="left(1)">&gt;</li>
     </ul>
  </div>
</template>

<script>
export default {
     props:{
        slides:{
          type:Array,
          default:[]
        },
        inv:{

          default:1000
        }
     },
     data(){  
       return{
          slidesIndex:1
       }
     },
     methods:{
        goto(index){
          this.slidesIndex=index
        },
        left(num){
          if(this.slidesIndex+num>this.slides.length){
            this.slidesIndex=0
          }else if(this.slidesIndex+num<1){
            this.slidesIndex=this.slides.length-1
          }else{
              this.slidesIndex+=num
          }
        },
        dingshifun(){
          this.invId =self.setInterval(() =>{
            this.left(1)
          },this.inv)
        },
        clearup(){
           clearInterval(this.invId)
        }
     },
     mounted(){
         this.dingshifun()
     },
     watch:{
        slidesIndex: function(){
          this.$emit('onchange',this.slidesIndex)
        }
        
     }

}
</script>

<style scoped>
.slide-trans-enter-active {
  transition: all .5s;
}
.slide-trans-enter {
  transform: translateX(900px);
}
.slide-trans-old-leave-active {
  transition: all .5s;
  transform: translateX(-900px);
}
.slide-show {
  position: relative;
  margin: 15px 15px 15px 0;
  width: 900px;
  height: 500px;
  overflow: hidden;
}
.slide-show h2 {
  position: absolute;
  width: 100%;
  height: 100%;
  color: #fff;
  background: #000;
  opacity: .5;
  bottom: 0;
  height: 30px;
  text-align: left;
  padding-left: 15px;
}
.slide-img {
  width: 100%;
}
.slide-img img {
  width: 100%;
  position: absolute;
  top: 0;
}
.slide-pages {
  position: absolute;
  bottom: 10px;
  right: 15px;
}
.slide-pages li {
  display: inline-block;
  padding: 0 10px;
  cursor: pointer;
  color: #fff;
}
.slide-pages li .on {
  text-decoration: underline;
}
</style>
