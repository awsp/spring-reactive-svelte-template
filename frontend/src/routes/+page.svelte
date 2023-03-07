<script lang="ts">
  import type {Article} from '../resources';
  import {AuthStatus} from '../resources.d';

  let article: Article = {
    title: 'article title',
    body: 'article body is not required. ',
    author: {
      email: 'a@example.com'
    }
  };

  let authStatus: AuthStatus = AuthStatus.LOGOUT
</script>

<h1>Welcome to SvelteKit</h1>
<p>Visit <a href="https://kit.svelte.dev">kit.svelte.dev</a> to read the documentation</p>

<p>Your authentication status: {authStatus}</p>

<h4>Article:</h4>
<p>Title: {article.title}</p>
<p>Body: {article.body}</p>
<p>Author: {article.author?.email}</p>
