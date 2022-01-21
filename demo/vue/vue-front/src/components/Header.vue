<template>
  <div class="mb-5">
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-col cols="1"></b-col>
      <b-navbar-brand to="/" class="ml-5">WeT</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-navbar-nav class="ml-3">
          <b-nav-item-dropdown class="" text="630" center>
            <b-dropdown-item to="map"><b-icon-github></b-icon-github></b-dropdown-item>
            <b-dropdown-item href="#">ES</b-dropdown-item>
            <b-dropdown-item href="#">RU</b-dropdown-item>
            <b-dropdown-item href="#">FA</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-item to="login">Login</b-nav-item>
        </b-navbar-nav>
        <b-col cols="1"></b-col>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: "header",
}
</script>