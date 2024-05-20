package com.mon3m.ktorexplorer.data.remote

import com.mon3m.ktorexplorer.data.HttpRoutes
import com.mon3m.ktorexplorer.data.remote.dto.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url

class ApiServiceImpl(private val client: HttpClient) : ApiService {
    override suspend fun getPosts(): List<PostResponse> {
        return try {
            client.get {
                url(HttpRoutes.POSTS) }
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }



}