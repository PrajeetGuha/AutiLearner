package stcet.project.autilearner.data

class UserDataManager private constructor() {
    private var data: Map<String,Any?> = mutableMapOf()

    fun getData(): Map<String,Any?>  {
        return data
    }

    fun setData(newData: MutableMap<String, Any?>) {
        data = newData
    }

    companion object {
        private var instance: UserDataManager? = null

        fun getInstance(): UserDataManager {
            if (instance == null) {
                instance = UserDataManager()
            }
            return instance!!
        }
    }
}
