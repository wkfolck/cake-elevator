<template>
  <div>
      <h2>Add Option</h2>
      <form v-on:submit="saveCakeOption()">
        <div>
        <label for="optionName">Name: </label>
        <input type="text" name="optionName" v-model="newOption.optionName" maxlength="25">
        </div>
        <div>
          <label for ="optionType">Type of Option: </label>
        <select name="optionType" v-model="newOption.optionType">
          <option value="Flavor">Flavor</option>
          <option value="Icing">Icing</option>
          <option value="Filling">Filling</option>
          <option value="Size">Size</option>
          <option value="Shape">Shape</option>
        </select>
        </div>
        <div>
          <label for="additionalPrice">Additional Price: $</label>
          <input name="additionalPrice" type="number" min="0.00" v-model="newOption.additionalPrice">
        </div>
        <div>
        <input class="navButton" type="submit"></div>
      </form>
  </div>
</template>

<script>
import orderService from '../services/OrderService'
export default {
    name: 'add-option',
    data() {
      return {
        newOption: {additionalPrice: 0.00}
      }
    },
    methods: {
      saveCakeOption() {
        orderService.saveCakeOption(this.newOption).then(response => {
          console.log(response);
        })
        .catch(error => {
          console.log(error.data);
        });
        }
      }
}
</script>

<style>

</style>