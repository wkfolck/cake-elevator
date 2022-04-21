<template>
  <h1>
    <button id="logout">Logout</button>

    </h1>
</template>

<script>
export default {
  created() {
    this.$store.commit("LOGOUT");
    this.$router.push("/customerhome");
  }
};
</script>
