<template>
    <div class="list row">
      <div class="col-md-8">
        <div class="input-group mb-3">
          <input type="text" class="form-control" placeholder="Search by name"
            v-model="name"/>
          <div class="input-group-append">
            <button class="btn btn-outline-secondary" type="button"
              @click="searchName"
            >
              Search
            </button>
          </div>
        </div>
      </div>
      <div class="col-md-6">
        <h4>Clients List</h4>
        <ul class="list-group">
          <li class="list-group-item"
            :class="{ active: index == currentIndex }"
            v-for="(client, index) in clients"
            :key="index"
            @click="setActiveClient(client, index)"
          >
            {{ client.name }}
          </li>
        </ul>
  
        <button class="m-3 btn btn-sm btn-danger" @click="removeAllClients">
          Remove All
        </button>
      </div>
      <div class="col-md-6">
        <div v-if="currentClient">
          <h4>Client</h4>
          <div>
            <label><strong>Name:</strong></label> {{ currentClient.name }}
          </div>
  
          <router-link :to="'/clients/' + currentClient.id" class="badge badge-warning">Edit</router-link>
        </div>
        <div v-else>
          <br />
          <p>Please click on a Client...</p>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import ClientDataService from "../services/ClientDataService";
  
  export default {
    name: "clients-list",
    data() {
      return {
        clients: [],
        currentClient: null,
        currentIndex: -1,
        name: ""
      };
    },
    methods: {
      retrieveClients() {
        ClientDataService.getAll()
          .then(response => {
            this.clients = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
      },
  
      refreshList() {
        this.retrieveClients();
        this.currentClient = null;
        this.currentIndex = -1;
      },
  
      setActiveClient(client, index) {
        this.currentClient = client;
        this.currentIndex = client ? index : -1;
      },
  
      removeAllClients() {
        ClientDataService.deleteAll()
          .then(response => {
            console.log(response.data);
            this.refreshList();
          })
          .catch(e => {
            console.log(e);
          });
      },
      
      searchName() {
        ClientDataService.findByName(this.name)
          .then(response => {
            this.clients = response.data;
            this.setActiveClient(null);
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
      }
    },
    mounted() {
      this.retrieveClients();
    }
  };
  </script>
  
  <style>
  .list {
    text-align: left;
    max-width: 750px;
    margin: auto;
  }
  </style>