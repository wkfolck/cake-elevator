<template>
<div>
  <h1>Add and Delete Cake Options</h1>
  <div id="addOptions">
    <add-option />
  </div>
  <div id="deleteOptions">
      <delete-option />
  </div>
</div>
</template>

<script>
import DeleteOption from '../components/DeleteOption.vue'
import AddOption from '../components/AddOption.vue'
export default {
  components: { DeleteOption, AddOption },

}
</script>

<style>

</style>