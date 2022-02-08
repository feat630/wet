<template>
  <div class="hello">
    <h1>전체 목록</h1>
    <b-skeleton-wrapper id="wrapper" :loading="loading" class="w-75 mt-5">
      <template #loading>
        <br>
        <b-skeleton-table id="skeletonTable"
          :rows="10"
          :columns="4"
          :table-props="{ bordered: false, striped: true }"
        ></b-skeleton-table>
      </template>
      <b-container>
        <b-row>
          <b-col sm="2">
            <b-form-select
              v-model="selected"
              :options="options"
              class="mb-3"
              value-field="item"
              text-field="name"
              disabled-field="notEnabled"
            ></b-form-select>
          </b-col>
          <b-col sm="9"></b-col>
          <b-col sm="1"><b-button @click="excelDown">Excel</b-button></b-col>
        </b-row>
      </b-container>
      <b-table fixed id="table" 
        striped hover 
        :fields="fields" 
        :items="res_list.data" 
        :per-page="perPage" 
        :current-page="currentPage" 
        class="w-75"
      ></b-table>
      <b-pagination id="paginations"
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        first-number
        last-number
        aria-controls="table"
        limit=9
        align="center"
      >
      <b-button>목록추가</b-button>
      </b-pagination>
    </b-skeleton-wrapper>

    
  </div>
</template>

<script>
import http from "../http-common";
import Xlsx from 'xlsx'

export default {
  name: 'HelloWorld',
  data(){
    return{
      res_list:[],
      fields:[
        {
          key: 'resId',
          label: '번호',
          thStyle: {width: '70px'},
          sortable: true
        },
        {
          key: 'resName',
          label: '상호명',
          sortable: true
        },
        {
          key: 'resType',
          label: '종류',
          thStyle: {width: '120px'},
          sortable: true
        },
        // {
        //   key: 'resAddrRoad',
        //   label: '주소',
        //   sortable: true
        // },
        // {
        //   key: 'resHoliday',
        //   label: '휴무일',
        //   sortable: true
        // },
        {
          key: 'resFamousMenu',
          label: '대표메뉴',
          sortable: true
        }
      ],
      perPage: 10,
      currentPage: 1,
      loading: true,

      selected: '10',
        options: [
          { item: '10', name: '10' },
          { item: '20', name: '20' },
          { item: '50', name: '50' },
        ]
    }
  },
  methods:{
    axProtocol(){
      http
        .get("/wet/list")
        .then(response=>{
          this.res_list = response.data;
          console.log(response.data);
          setTimeout(() => this.loading = false, 300);
        })
        .catch(e=>{
          console.log(e);
        })
    },
    excelDown() {
      const workBook = Xlsx.utils.book_new()
      const workSheet = Xlsx.utils.json_to_sheet(this.res_list.data)
      Xlsx.utils.book_append_sheet(workBook, workSheet, 'example')
      Xlsx.writeFile(workBook, 'example.xlsx')
    },
  },
  mounted(){
    // setTimeout(() => this.axProtocol(), 2000);
    this.axProtocol();
  },
  computed: {
      rows() {
        return this.res_list.data.length
      }
    }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
.num-width {
  width:700px;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
#table, #wrapper {
  width: 90%;
  margin:auto;
}
#paginations{
  margin: 30px 0;
}
#selec{
  width: 10%;
}
</style>
