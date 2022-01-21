<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h2>Essential Links</h2>
    <b-button to="/main">Go to Main Page</b-button>
    <b-button to="/map">Go to Map</b-button>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>


<style scoped>
h1, h2 {
  font-weight: normal;
}

</style>
