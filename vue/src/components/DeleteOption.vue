<template>
  <div>
      <h2>Delete Option</h2>
      <select v-model="currentOptionId">
          <option v-for="option in cakeOptions" v-bind:key="option.optionId" :value="option.optionId">{{option.optionType}}: {{option.optionName}}</option>
      </select>
      <button class= "navButton" v-on:click="deleteCakeOption()">Delete Option</button>
  </div>
</template>

<script>
import orderService from '../services/OrderService'
export default {
name: 'delete-option',
data() {
        return {
            cakeOptions: [],
            currentOptionId: null
        }
    },
    created() {
            orderService.getCakeOptions().then(
                response => {
                    console.log(response.data);
                    this.cakeOptions = response.data;
                }
            );
        },
        methods: {
            deleteCakeOption() {
                if(!this.currentOptionId) {
                    alert("You must select a cake option to delete!");
                } else {
                    orderService.deleteCakeOption(this.currentOptionId).then(response => {
                        console.log(response);
                        this.$router.go();
                    });
                }
            }
        }
}
</script>

<style>

</style>