<template>
  <div class="mt-5">
        <hr>
        <b-container class="bv-example-row" id="container">
            <b-row id="rows">
                <b-col>email</b-col>
                <b-col>GitHub</b-col>
                <b-col>Discord</b-col>
            </b-row>
        </b-container>
  </div>
</template>

<script>
export default {
  name: "Footer",
}
</script>
<style>
#container{
    text-align: center;
    margin-top: 20px;
    margin-bottom: 30px;
}
#rows{
    width: 60%;
    margin: auto;
    color: dimgrey;
}
#footcol, a{
  text-align: center;
}
</style>