<template>
  <div class="hello">
    <h1 class="mb-3">전체 목록</h1>
    <b-skeleton-wrapper id="wrapper" :loading="loading" class="w-75 mt-5">
      	<template #loading>
        	<br>
        	<b-skeleton-table id="skeletonTable"
        	  :rows="10"
        	  :columns="4"
        	  :table-props="{ bordered: false, striped: true }"
        	>
			</b-skeleton-table>
      	</template>
		<p style="text-align:left" class="w-75 ml-auto mr-auto">
			<b-row align-h="between">
			<b-col style="text-align:left;">
				<b-form-select
					v-model="selected"
					:options="options"
					value-field="item"
					text-field="name"
					disabled-field="notEnabled"
					style="width:15%;"
					>
				</b-form-select>
			</b-col>
			<b-col style="text-align:right;"><b-button @click="excelDown">Excel</b-button></b-col>
			</b-row>
		</p>
      	<b-table fixed id="table" 
			striped hover 
			:fields="fields" 
			:items="res_list.data" 
			:per-page="selected" 
			:current-page="currentPage" 
			selectable
			select-mode="single"
			@row-clicked="onRowSelected"
			v-b-toggle.sidebar-right
			class="w-75"
		>
      	</b-table>
		<b-pagination id="paginations"
			v-model="currentPage"
			:total-rows="rows"
			:per-page="selected"
			first-number
			last-number
			aria-controls="table"
			limit=9
			align="center"
		>
		</b-pagination>
		<b-button to="/addRestaurant">목록추가</b-button>

      	<b-sidebar id="sidebar-right" title="식당정보" style="width," right shadow backdrop>
			<b-card
			:title=selOne.resName
			img-src="https://picsum.photos/600/300/?image=25"
			img-alt="Image"
			img-top
			tag="article"
			style="max-width: 90%;"
			class="mb-2 ml-auto mr-auto"
			>
			<b-card-text>
				{{selOne.resType}} | {{selOne.resAddrRoad}}<br>
				{{selOne.resHoliday}}<br>
				{{selOne.resFamousMenu}}
			</b-card-text>
			</b-card>
        

			<b-card
			v-for="list in wreview.data" :key="list.userId"
			:title=list.resId
			img-src="https://picsum.photos/600/300/?image=25"
			img-alt="Image"
			img-top
			tag="article"
			style="max-width: 90%;"
			class="mb-2 ml-auto mr-auto"
			>
			<b-card-text> 
				{{list.userReview}}<br>
				{{list.date}}<br>
				{{list.image}}
				<b-form-rating v-model="list.rating" class="w-75 ml-auto mr-auto" readonly></b-form-rating>
			</b-card-text>
			</b-card>

			<div id="sidebar-div" class="px-3 py-3 text-center">
			<b-button :to="{name: 'write', params: {wlist}}" class="mr-auto ml-auto">리뷰쓰러가기</b-button>
			</div>
		</b-sidebar>
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
      selOne:[],
      wreview:[],
      wlist:{
        data:[
        
        ]
      },
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
        {
          key: 'resFamousMenu',
          label: '대표메뉴',
          sortable: true
        },
      ],
      perPage: 10,
      currentPage: 1,
      loading: true,

      selected: 10,
        options: [
          { item: 10, name: '10' },
          { item: 20, name: '20' },
          { item: 50, name: '50' },
        ]
    }
  },
  methods:{
    axProtocol(){
      http
        .get("/restaurant/list")
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
    onRowSelected(items) {
        this.selOne = items;
        this.wlist.data[0] = items;
        http
          .get("/review/one", {
            params: {
              resId: items.resId
            }
          })
          .then(response=>{
            this.wreview = response.data;
          })
          .catch(e=>{
            console.log(e);
          });
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
