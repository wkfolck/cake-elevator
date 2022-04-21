<template>
<div class="search-results">
  <order-search />
</div>
</template>

<script>
import OrderSearch from '../components/OrderSearch.vue'
export default {
  components: { OrderSearch },

}
</script>

<style>

.search-results {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

</style>